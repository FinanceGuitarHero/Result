package com.hero.backend.service;

import com.hero.backend.dto.BankDto;
import com.hero.backend.enity.BankAccount;
import com.hero.backend.repo.BankAccountRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankIntegrationService bankIntegrationService;

    private final BankAccountRepo bankAccountRepo;

    private final TgUserService tgUserService;

    @Transactional
    public Optional<Boolean> addBank(BankDto bankDto){
        if (bankAccountRepo.existsByName(bankDto.name())){
            return Optional.of(false);
        }
        var user = tgUserService.getUserByTgId(bankDto.id());
        if (user.isEmpty()){
            return Optional.empty();
        }
        BankAccount bankAccount = new BankAccount(bankDto.name(), bankDto.telephone(), user.get());
        boolean ok = bankIntegrationService.addBank(bankAccount);
        if (ok){
            return Optional.of(true);
        }
        return Optional.empty();
    }
}
