package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    String getName();

    String getDescription();

    SendMessage handle(Update update);
}
