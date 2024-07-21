package com.hero.botClient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    @Value("${queues.commands_queue}")
    private String commandsQueue;

    private final RabbitTemplate rabbitTemplate;

    public void sendCommand(Update update) {
        rabbitTemplate.convertAndSend(commandsQueue, update);
        log.info("Команда {} отправлена на сервер", update.getMessage().getText());
    }
}
