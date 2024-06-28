package com.example.bot.listeners;

import com.example.bot.services.MessageService;
import com.pengrad.telegrambot.UpdatesListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TgUpdatesListener {
    private final UpdatesListener tgUpdatesListener;

    public TgUpdatesListener(MessageService messageService) {
        this.tgUpdatesListener = list -> {
            list.forEach(messageService::handleMessage);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }
}
