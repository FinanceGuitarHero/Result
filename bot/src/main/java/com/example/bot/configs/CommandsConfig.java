package com.example.bot.configs;

import com.example.bot.commands.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CommandsConfig {

    public static List<Command> COMMANDS = new ArrayList<>();

    public CommandsConfig(@NotNull TelegramBot telegramBot) {
        COMMANDS.add(new AddBankCommand());
        COMMANDS.add(new AddTransactionCommand());
        COMMANDS.add(new GetFinancialTipsCommand());
        COMMANDS.add(new GetTransactionsCommand());
        COMMANDS.add(new HelpCommand());
        COMMANDS.add(new PrintPdfCommand());
        COMMANDS.add(new StartCommand());
        telegramBot.execute(this.createCommandMenu());
    }

    public SetMyCommands createCommandMenu() {
        return new SetMyCommands(COMMANDS.stream().map(command -> new BotCommand(
                command.getName(),
                command.getDescription()
        )).toArray(BotCommand[]::new));
    }
}
