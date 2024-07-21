package com.hero.backend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queues.answer_queue}")
    private String answerQueue;

    @Bean
    public Queue answerQueue() {
        return new Queue(answerQueue);
    }
    @Bean
    MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
