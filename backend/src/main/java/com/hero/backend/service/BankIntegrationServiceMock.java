package com.hero.backend.service;

import com.hero.backend.enity.BankAccount;
import com.hero.backend.repo.BankAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankIntegrationServiceMock implements BankIntegrationService{

    private final BankAccountRepo bankAccountRepo;

    @Override
    public boolean addBank(BankAccount bankAccount) {
        //типо проверка на верность данных
        //проверка успешно пройдена
        bankAccountRepo.save(bankAccount);
        return true;
    }
}
