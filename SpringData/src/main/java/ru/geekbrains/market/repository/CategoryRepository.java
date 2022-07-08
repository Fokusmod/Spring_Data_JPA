package ru.geekbrains.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);
}
