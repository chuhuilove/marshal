/**
 * 这个包下有三个注解:
 * 1. {@code EnableMarshalConsumer}使用这个注解,微服务将作为消费者
 * 2. {@code EnableMarshalProducer}使用这个注解,微服务将作为生产者
 * 3. {@code EnableMarshal}使用这个注解,微服务既作为生产者,也作为消费者
 *
 * 这三个注解,在框架层面属于互斥的,也就是说,每一个微服务应用,只能使用其中的一个注解.
 * 如果微服务作为生产者,则使用{@code @EnableMarshalProducer}注解,以此类推...
 *
 * 另外,每个注解在任意一个微服务层面中,只能出现一次,例如创建一个微服务A,其角色既是生产者,也是消费者
 * 那么就使用{@code @EnableMarshal}注解,且,在整个微服务中,这个注解只能出现一次.\
 *
 *
 *
 */
package com.chuhui.marshal.client.annotation;

