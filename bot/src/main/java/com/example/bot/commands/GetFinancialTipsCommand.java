package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class GetFinancialTipsCommand implements Command {
    public static String NAME = "/get_tips";
    private String DESCRIPTION = "получить финансовые советы";

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
        return new SendMessage(chatId, NAME);
    }
}
