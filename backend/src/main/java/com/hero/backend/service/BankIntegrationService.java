package com.hero.backend.service;

import com.hero.backend.dto.BankDto;
import com.hero.backend.enity.BankAccount;

public interface BankIntegrationService {
    boolean addBank(BankAccount bankAccount);
}
