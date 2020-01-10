# marshal

## marshal-server 

一个负责存储的服务,和具体的数据格式无关.

## marshal-client

连接marshal-server的客户端,获取什么数据,也和数据格式无关.

> marhsal-server和marshal-client 和spring Ioc应该是没有任何关联关系.


这样就行了,redis是一个key-value数据库

zk是一个基于树的数据库

marshal-server对标的是redis和zk....

marsha-server作为一个注册中心,它不香吗?

明白自己现在做的是一个微服务框架呢?还是一个分布式协调服务中心?
















