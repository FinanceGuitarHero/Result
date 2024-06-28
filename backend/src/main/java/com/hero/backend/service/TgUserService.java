package com.hero.backend.service;

import com.hero.backend.dto.BalanceResponseDto;
import com.hero.backend.dto.StartDto;
import com.hero.backend.dto.UserIdDto;
import com.hero.backend.enity.TgUser;
import com.hero.backend.enity.Transaction;
import com.hero.backend.repo.TgUserRepo;
import com.hero.backend.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TgUserService {

    private final TgUserRepo tgUserRepo;

    private final TransactionRepo transactionRepo;

    private final AiService aiService;

    public Optional<TgUser> register(StartDto startDto) {
        if (tgUserRepo.existsByTgId(startDto.id())){
            return Optional.empty();
        }
        TgUser user = new TgUser(startDto.name(), startDto.id());
        tgUserRepo.save(user);
        return Optional.of(user);
    }

    public Optional<TgUser> getUserByTgId(String id) {
        return tgUserRepo.findByTgId(id);
    }

    public Optional<BalanceResponseDto> getBalance(UserIdDto userIdDto) {
        var user = getUserByTgId(userIdDto.id());
        if(user.isEmpty()){
            return Optional.empty();
        }
        List<Transaction> list = transactionRepo.findAllByUserId(user.get().getId());
        Long balance = 0L;
        for (Transaction transaction : list) {
            Long amount = transaction.getAmount();
            boolean type = transaction.isType();
            if (type){
                balance += amount;
            }
            else {
                balance -= amount;
            }
        }
        var response = new BalanceResponseDto(balance, aiService.getDescription(userIdDto));
        return Optional.of(response);
    }
}
