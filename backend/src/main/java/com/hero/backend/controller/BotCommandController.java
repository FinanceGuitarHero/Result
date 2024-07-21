package com.hero.backend.controller;

import com.hero.backend.config.BotCommand;
import com.hero.backend.config.CommandsController;
import com.hero.backend.dto.*;
import com.hero.backend.service.BankAccountService;
import com.hero.backend.service.TgUserService;
import com.hero.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@CommandsController
@RequiredArgsConstructor
public class BotCommandController {

    private final TgUserService tgUserService;

    private final BankAccountService bankAccountService;

    private final TransactionService transactionService;

    @BotCommand("/start")
    public String handleStart(Update update) {
        var name = update.getMessage().getFrom().getFirstName();
        var chatId = update.getMessage().getChatId();
        var ok = tgUserService.register(new StartDto(name, chatId.toString()));
        if (ok.isEmpty()){
            return "Ошибка при регистрации";
        }
        return String.format("Приветствую, %s!", name);
    }

    @BotCommand("/add_bank")
    public String handleAddBank(Update update) {
        //TODO сделать добавление транзакций из банка
        var chatId = update.getMessage().getChatId();
        var bankName = "Tinkoff";
        var telephone = "+79812829132";
        var ok = bankAccountService.addBank(new BankDto(chatId.toString(), bankName, telephone));
        if (ok.isEmpty()){
            return "Ошибка добавления банка";
        }
        return "Bank Created";
    }

    @BotCommand("/getTransactions")
    public String getTransactionsById(Update update){
        var list = transactionService.getTransactionsById(update.getMessage().getChatId().toString());
        return list.map(Object::toString).orElseGet(() -> "Ошибка");
    }

    @BotCommand("/getBalance")
    public String getBalance(Update update){
        var balance = tgUserService.getBalance(new UserIdDto(update.getMessage().getChatId().toString()));
        return balance.map(aLong -> aLong.balance() + " " + aLong.text()).orElseGet(() -> "Ошибка");
    }

    @BotCommand("/unknown")
    public String handleUnknown(Update update) {
        return "Unknown command: " + update.getMessage().getText();
    }
}
