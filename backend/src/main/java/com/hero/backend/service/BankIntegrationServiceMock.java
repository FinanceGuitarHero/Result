package com.hero.backend.service;

import com.hero.backend.dto.TransactionDto;
import com.hero.backend.enity.BankAccount;
import com.hero.backend.repo.BankAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankIntegrationServiceMock implements BankIntegrationService{

    private final BankAccountRepo bankAccountRepo;

    private final TransactionService transactionService;

    @Override
    public boolean addBank(BankAccount bankAccount) {
        //типо проверка на верность данных
        //проверка успешно пройдена
        bankAccountRepo.save(bankAccount);
        //импорт транзакций
        boolean type = false;
        for (int i = 0; i < 15; i++) {
            long amount = (long) (Math.random() * 1000);
            TransactionDto transactionDto = new TransactionDto(bankAccount.getUser().getTgId(),
                    Categories.values()[i%4].toString(), type, amount);
            transactionService.addTransaction(transactionDto);
        }
        return true;
    }

    private enum Categories{
        ПУТЕШЕСТВИЯ, ПРОДУКТЫ, БИЗНЕС, РАЗВЛЕЧЕНИЯ
    }
}
