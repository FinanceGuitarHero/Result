package com.hero.backend.repo;

import com.hero.backend.enity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
