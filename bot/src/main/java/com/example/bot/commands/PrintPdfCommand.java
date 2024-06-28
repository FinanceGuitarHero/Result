package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class PrintPdfCommand implements Command {
    public static String NAME = "/print_pdf";
    private String DESCRIPTION = "получить отчет в формате pdf";

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
