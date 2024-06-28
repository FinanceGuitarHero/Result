package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

public class StartCommand implements Command {

    public static final String NAME = "/start";
    private String DESCRIPTION = "зарегистрироваться в боте";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(chatId, "Вы успешно зарегистрировались!");
    }
}
