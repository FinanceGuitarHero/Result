package com.hero.botClient.service;

import com.hero.botClient.controller.TgBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Slf4j
@Service
public class ConsumerService {

    private final TgBot tgBot;

    @RabbitListener(queues = "${queues.answer_queue}")
    public void consumeAnswer(SendMessage sendMessage){
        log.info("Сообщение {} пришло клиенту, происходит пересылка в телеграм", sendMessage.getText());
        tgBot.sendMessage(Long.parseLong(sendMessage.getChatId()), sendMessage.getText());
    }
}