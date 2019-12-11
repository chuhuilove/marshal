package com.chuhui.marshal.server.serverfactory;

import com.chuhui.marshal.MarshalStandaloneMain;
import com.chuhui.marshal.framework.config.MarshalConfig;
import com.chuhui.marshal.framework.utils.ServerFactoryUtils;
import com.chuhui.marshal.server.ServerContextFactory;
import com.chuhui.marshal.server.servercontext.NettyServerContext;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * NettyServerFactory
 * 基于netty的服务工厂
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:
 */
public class NettyServerFactory extends ServerContextFactory {
    private static final Logger LOG = LoggerFactory.getLogger(NettyServerFactory.class);
    private ServerBootstrap serverBootstrap;

    private InetSocketAddress localAddress;
    private int maxClientCnnections = 60;

    private volatile boolean serverStarted;

    private static final AttributeKey<NettyServerContext> CONNECTION_ATTRIBUTE =
            AttributeKey.valueOf("NettyServerContext");

    private final ChannelGroup allChannels =
            new DefaultChannelGroup("marshalServerContext", new DefaultEventExecutor());


    /**
     * 存储ip地址和客户端
     * 这个客户端,可以是生产者,也可以是消费者...
     */
    private final Map<InetAddress, Set<NettyServerContext>> ipMap = new HashMap<>();

    private ContextChannelHandler serverChannelHandler = new ContextChannelHandler();


    public NettyServerFactory() {

        EventLoopGroup bossGroup = ServerFactoryUtils.getNioOrEpollGroup(1);

        EventLoopGroup workerGroup = ServerFactoryUtils.getNioOrEpollGroup();


        // 配置文件的问题,需要进一步的细化...
        // 这种方式,对于以后的集群模式而言.有很大的弊端
        MarshalConfig globalConfig = MarshalStandaloneMain.getGlobalConfig();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(ServerFactoryUtils.getNioOrEpollChannel())
                // parent channel options
                .option(ChannelOption.SO_REUSEADDR, true)
                // child channels options
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_LINGER, -1)

                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(globalConfig.getRcvbufAllocatorSize()))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("serverFactory", serverChannelHandler);
                    }
                });
    }

    @Override
    public void configure(InetSocketAddress socketaddress, int maxConns) {
        localAddress = socketaddress;
        maxClientCnnections = maxConns;
    }

    @Override
    public void startup() {
        if (!serverStarted) {
            start();
        }
    }

    @Override
    public synchronized void start() {

        LOG.info("binding to port {}", localAddress);
        Channel parentChannel = serverBootstrap.bind(localAddress).syncUninterruptibly().channel();
        /**
         * 如果原始端口为0,则bind()之后的端口会更改,
         * 则更新localAddress以获取实际端口.
         */
        localAddress = (InetSocketAddress) parentChannel.localAddress();
        LOG.info("actual bound to port " + getLocalPort());
    }


    @Override
    public int getLocalPort() {
        return localAddress.getPort();
    }


    @ChannelHandler.Sharable
    class ContextChannelHandler extends ChannelDuplexHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelActive method...");
            NettyServerContext context = new NettyServerContext(ctx.channel(), NettyServerFactory.this);
            ctx.channel().attr(CONNECTION_ATTRIBUTE).set(context);
            allChannels.add(ctx.channel());
            addContext(context);

        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelRegistered method...");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            allChannels.remove(ctx.channel());
            NettyServerContext context = ctx.channel().attr(CONNECTION_ATTRIBUTE).getAndSet(null);
            if (context != null) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("Channel inactive caused close {}", context);
                }
                context.close();
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {

                ByteBuf byteBuf = (ByteBuf) msg;
                NettyServerContext context = ctx.channel().attr(CONNECTION_ATTRIBUTE).get();
                context.processMessage(byteBuf);

                // 堆外内存,直接调用array()方法,会出现的 java.lang.UnsupportedOperationException: direct buffer异常///
                // https://stackoverflow.com/questions/52658774/netty-io-netty-buffer-bytebuf-array-throws-exception-direct-buffer

            } finally {
                ReferenceCountUtil.release(msg);
            }
        }


        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelReadComplete method...");
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            LOG.info("invoked userEventTriggered method...evt:{}", evt);
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            Channel channel = ctx.channel();
            channel.close();
            LOG.error("error occurs", cause);
        }
    }

    private void addContext(NettyServerContext context) {
        contexts.add(context);
        synchronized (ipMap) {
            InetAddress addr =
                    ((InetSocketAddress) context.getChannel().remoteAddress()).getAddress();
            Set<NettyServerContext> s = ipMap.get(addr);
            if (s == null) {
                s = new HashSet<>();
                ipMap.put(addr, s);
            }
            s.add(context);
        }
    }


}
