package com.example.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;

import static com.example.bot.configs.CommandsConfig.COMMANDS;

public class HelpCommand implements Command {
    public static final String NAME = "/help";
    private String DESCRIPTION = "вывести сообщение с командами";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(@NotNull Update update) {
        long chatId = update.message().chat().id();
        StringBuilder answer = new StringBuilder();
        answer.append("Список доступных команд:\n");
        for (Command command : COMMANDS) {
            answer
                    .append(command.getName())
                    .append(" - ")
                    .append(command.getDescription())
                    .append("\n");
        }
        return new SendMessage(chatId, String.valueOf(answer));
    }
}
