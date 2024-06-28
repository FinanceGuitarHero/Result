package com.example.bot.configs;

import com.example.bot.listeners.TgUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TgUpdatesListenerConfig {
    public TgUpdatesListenerConfig(
            @NotNull TelegramBot telegramBot,
            @NotNull TgUpdatesListener tgUpdatesListener) {

        telegramBot.setUpdatesListener(tgUpdatesListener.getTgUpdatesListener());
    }
}
