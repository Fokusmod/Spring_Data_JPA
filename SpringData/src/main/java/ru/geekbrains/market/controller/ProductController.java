package ru.geekbrains.market.controller;

import lombok.RequiredArgsConstructor;;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.exception.ResourceNotFoundException;
import ru.geekbrains.market.model.Category;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.service.CategoryService;
import ru.geekbrains.market.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;


    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(@RequestParam(name = "i", defaultValue = "1") int pageIndex) {
        return productService.findCatalog(pageIndex - 1, 10).map(ProductDto::new);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto new_product) {
        Product product = new Product();
        product.setTitle(new_product.getTitle());
        product.setPrice(new_product.getPrice());
        Category category = categoryService.findByTitle(new_product.getCategoryTitle())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category title = " + new_product.getCategoryTitle() + " not found"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);

    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto change(@RequestBody ProductDto change_product) {
        Product product = productService.findByTitle(change_product.getTitle())
                .orElseThrow(() -> new ResourceNotFoundException("This product = " + change_product.getTitle() + " not found"));
        product.setPrice(change_product.getPrice());
        productService.save(product);
        return new ProductDto(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAll() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}









