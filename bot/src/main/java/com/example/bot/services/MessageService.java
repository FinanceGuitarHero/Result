package com.example.bot.services;

import com.example.bot.commands.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static com.example.bot.configs.CommandsConfig.COMMANDS;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final TelegramBot telegramBot;

    public void handleMessage(@NotNull Update update) {
        String chatMessage = update.message().text();

        for (Command command : COMMANDS) {
            if (command.getName().equals(chatMessage)) {
                telegramBot.execute(command.handle(update));
            }
        }
    }
}
