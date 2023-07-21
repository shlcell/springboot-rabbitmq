package com.shl.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean("consumer1RabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory consumer1RabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 为第一个消费者设置QoS为10
        factory.setPrefetchCount(10);
        return factory;
    }

    @Bean("consumer2RabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory consumer2RabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 为第二个消费者设置QoS为5
        factory.setPrefetchCount(5);
        return factory;
    }
}
