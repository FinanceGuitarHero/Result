package com.hero.backend.controller;

import com.hero.backend.dto.*;
import com.hero.backend.enity.Transaction;
import com.hero.backend.service.AiService;
import com.hero.backend.service.BankAccountService;
import com.hero.backend.service.TgUserService;
import com.hero.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor
public class HomeController {

    private final TgUserService tgUserService;

    private final BankAccountService bankAccountService;

    private final TransactionService transactionService;

    @PostMapping("start")
    public ResponseEntity<Void> start(@RequestBody StartDto startDto){
        var ok = tgUserService.register(startDto);
        if (ok.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("addBank")
    public ResponseEntity<Void> addBank(@RequestBody BankDto bankDto){
        //сделать добавление транзакций из банка
        var ok = bankAccountService.addBank(bankDto);
        if (ok.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("getTransactions")
    public ResponseEntity<List<TransactionInfoDto>> getTransactionsById(@RequestBody UserIdDto userIdDto){
        var list = transactionService.getTransactionsById(userIdDto.id());
        return list.map(transactionInfoDtos -> ResponseEntity.ok().body(transactionInfoDtos)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("addTransaction")
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionDto transactionDto){
        var ok = transactionService.addTransaction(transactionDto);
        if (ok.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("getBalance")
    public ResponseEntity<BalanceResponseDto> getBalance(@RequestBody UserIdDto userIdDto){
        var balance = tgUserService.getBalance(userIdDto);
        return balance.map(aLong -> ResponseEntity.ok().body(aLong)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
