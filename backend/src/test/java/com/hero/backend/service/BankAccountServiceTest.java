package com.hero.backend.service;

import com.hero.backend.dto.BankDto;
import com.hero.backend.enity.BankAccount;
import com.hero.backend.enity.TgUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @Mock
    BankIntegrationService bankIntegrationService;

    @Mock
    TgUserService tgUserService;

    @InjectMocks
    BankAccountService bankAccountService;

    @Test
    void addBank_withCorrectUserAndBank_returnsOptionalOfTrue() {
        //given
        var bankDto = new BankDto("1", "Denis", "+79998999121");
        var user = new TgUser("Denis" ,"1");
        var bankAcc = new BankAccount(bankDto.name(), bankDto.telephone(), user);

        when(tgUserService.getUserByTgId(bankDto.id())).thenReturn(Optional.of(user));
        when(bankIntegrationService.addBank(bankAcc)).thenReturn(true);
        //when
        var opt = bankAccountService.addBank(bankDto);
        //then
        assertNotNull(opt);
        assertFalse(opt.isEmpty());
        assertTrue(opt.get());
    }
    @Test
    void addBank_withIncorrectUser_returnsOptionalOfEmpty() {
        //given
        var bankDto = new BankDto("1", "Denis", "+79998999121");
        when(tgUserService.getUserByTgId(bankDto.id())).thenReturn(Optional.empty());
        //when
        var opt = bankAccountService.addBank(bankDto);
        //then
        assertNotNull(opt);
        assertTrue(opt.isEmpty());
    }
    @Test
    void addBank_withCorrectUserAndIncorrectBank_returnsOptionalOfEmpty() {
        //given
        var bankDto = new BankDto("1", "Denis", "+79998999121");
        var user = new TgUser("Denis" ,"1");
        var bankAcc = new BankAccount(bankDto.name(), bankDto.telephone(), user);

        when(tgUserService.getUserByTgId(bankDto.id())).thenReturn(Optional.of(user));
        when(bankIntegrationService.addBank(bankAcc)).thenReturn(false);
        //when
        var opt = bankAccountService.addBank(bankDto);
        //then
        assertNotNull(opt);
        assertTrue(opt.isEmpty());
    }
}