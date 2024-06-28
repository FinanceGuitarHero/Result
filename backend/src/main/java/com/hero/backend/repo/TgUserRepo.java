package com.hero.backend.repo;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.hero.backend.enity.TgUser;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TgUserRepo extends CrudRepository<TgUser, Long> {
    boolean existsByTgId(String tgId);

    Optional<TgUser> findByTgId(String tgId);
}
