package com.example.bot.backend;

import com.example.bot.backend.dto.RegisterDto;
import com.example.bot.backend.dto.TransactionDto;

import java.io.File;
import java.util.List;

public interface RestRepo {
    void start(RegisterDto registerDto);

    Integer addTransaction(TransactionDto transactionDto);

    String getTips(String userId);

    List<TransactionDto> getAllTransactions(String userId);

    String getBalance(String userId);

    File getPdf(String userId);
}
