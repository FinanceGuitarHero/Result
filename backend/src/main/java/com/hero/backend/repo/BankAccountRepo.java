package com.hero.backend.repo;

import com.hero.backend.enity.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepo extends CrudRepository<BankAccount, Long> {
    boolean existsByName(String name);
}
