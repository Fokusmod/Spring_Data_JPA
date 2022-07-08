package ru.geekbrains.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.model.Category;
import ru.geekbrains.market.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
       return categoryRepository.findAll();
    }

    public Optional<Category> findByTitle (String title) {
        return categoryRepository.findByTitle(title);
    }
}
