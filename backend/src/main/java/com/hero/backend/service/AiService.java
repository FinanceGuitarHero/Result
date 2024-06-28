package com.hero.backend.service;

import com.hero.backend.dto.UserIdDto;

public interface AiService {
    String getDescription(UserIdDto dto);
}
