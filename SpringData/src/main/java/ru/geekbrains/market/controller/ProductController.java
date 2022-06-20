package ru.geekbrains.market.controller;

import lombok.RequiredArgsConstructor;;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.service.ProductService;


//обработка ошибок

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> findAll(@RequestParam(name = "i", defaultValue = "1") int pageIndex) {
        return service.findCatalog(pageIndex - 1, 10);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void save (@RequestBody Product new_product) {
        Product product = new Product();
        product.setTitle(new_product.getTitle());
        product.setPrice(new_product.getPrice());
        service.save(product);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Product change (@RequestBody Product change_product){
        return service.change(change_product);
    }

    @DeleteMapping("/products/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }






}









