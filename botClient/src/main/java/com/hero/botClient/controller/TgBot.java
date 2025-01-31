package com.hero.botClient.controller;

import com.hero.botClient.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
@Slf4j
public class TgBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    private final String token;

    private final ProducerService producerService;

    public TgBot(@Value("${bot.token}") String botToken, ProducerService producerService) {
        this.token = botToken;
        telegramClient = new OkHttpTelegramClient(getBotToken());
        this.producerService = producerService;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    public void sendMessage(long id, String text){
        SendMessage message = SendMessage // Create a message object
                .builder()
                .chatId(id)
                .text(text)
                .build();
        try {
            telegramClient.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getText().startsWith("/")){
                producerService.sendCommand(update);
            }

//            String message_text = update.getMessage().getText();
//
//            long chat_id = update.getMessage().getChatId();
//            sendMessage(chat_id, message_text);
        }

    }
}