package com.hero.backend.service;

import com.hero.backend.enity.Category;
import com.hero.backend.repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category getCategory(String category){
        var exists = categoryRepo.findByName(category);
        return exists.orElseGet(() -> createCategory(category));
    }

    public Category createCategory(String name) {
        Category category = new Category(name);
        categoryRepo.save(category);
        return category;
    }
}
