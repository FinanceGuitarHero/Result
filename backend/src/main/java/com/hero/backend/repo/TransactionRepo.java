package com.hero.backend.repo;

import com.hero.backend.enity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(Long userId);
}
