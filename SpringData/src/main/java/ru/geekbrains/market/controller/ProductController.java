package ru.geekbrains.market.controller;

import lombok.RequiredArgsConstructor;;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.exception.DataValidationException;
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

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById (@PathVariable Long id){
        Product product = productService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        return new ProductDto(product);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductDto new_product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        productService.saveProductFromDto(new_product);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void change(@RequestBody @Validated ProductDto change_product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
       productService.updateProductFromDto(change_product);

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









