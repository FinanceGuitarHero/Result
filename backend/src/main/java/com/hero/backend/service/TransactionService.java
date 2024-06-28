package com.hero.backend.service;

import com.hero.backend.dto.TransactionDto;
import com.hero.backend.dto.TransactionInfoDto;
import com.hero.backend.enity.Category;
import com.hero.backend.enity.Transaction;
import com.hero.backend.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TgUserService tgUserService;

    private final CategoryService categoryService;

    private final TransactionRepo transactionRepo;

    public Optional<List<TransactionInfoDto>> getTransactionsById(String id) {
        var user = tgUserService.getUserByTgId(id);
        if (user.isEmpty()){
            return Optional.empty();
        }
        List<Transaction> transactions = transactionRepo.findAllByUserId(user.get().getId());
        List<TransactionInfoDto> transactionsDto = new LinkedList<>();
        for (Transaction transaction : transactions) {
            transactionsDto.add(new TransactionInfoDto(
                    transaction.getCategory().getName(),
                    transaction.isType(),
                    transaction.getAmount()));
        }
        return Optional.of(transactionsDto);
    }

    public Optional<Transaction> addTransaction(TransactionDto transactionDto) {
        var user = tgUserService.getUserByTgId(transactionDto.id());
        if (user.isEmpty()){
            return Optional.empty();
        }
        Category category = categoryService.getCategory(transactionDto.category());

        Transaction transaction = new Transaction(category, transactionDto.type(),
                transactionDto.amount(), user.get());
        transactionRepo.save(transaction);
        return Optional.of(transaction);
    }
}
