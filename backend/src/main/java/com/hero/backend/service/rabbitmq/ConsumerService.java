package com.hero.backend.service.rabbitmq;

import com.hero.backend.config.BotCommandControllerConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final ProducerService producerService;

    private final BotCommandControllerConfiguration botCommandControllerConfiguration;

    @RabbitListener(queues = "${queues.commands_queue}")
    public void consumeCommands(Update update){
        log.info("Команда {} принята на сервер", update.getMessage().getText());
        String message = botCommandControllerConfiguration.handle(update);
        SendMessage sendMessage = SendMessage
                        .builder()
                        .chatId(update.getMessage().getChatId())
                        .text(message)
                        .build();
        producerService.produceAnswer(sendMessage);
    }
}
