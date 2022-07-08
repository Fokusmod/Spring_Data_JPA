package ru.geekbrains.market.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.dto.CategoryDto;
import ru.geekbrains.market.model.Category;
import ru.geekbrains.market.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("api/v1/category")
    public List<CategoryDto> findAll (){
       return categoryService.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
