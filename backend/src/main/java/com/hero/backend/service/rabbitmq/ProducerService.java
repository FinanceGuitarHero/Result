package com.hero.backend.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    @Value("${queues.answer_queue}")
    private String answerQueue;

    private final RabbitTemplate rabbitTemplate;

    public void produceAnswer(SendMessage message) {
        rabbitTemplate.convertAndSend(answerQueue, message);
        log.info("Ответ <{}> отправлен клиенту", message.getText());
    }
}
