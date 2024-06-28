package com.hero.backend.service;

import com.hero.backend.dto.UserIdDto;
import org.springframework.stereotype.Service;

@Service
public class AiServiceMock implements AiService{
    @Override
    public String getDescription(UserIdDto dto) {
        return "Поздравляю! Ты едва сводишь концы с концами.";
    }
}
