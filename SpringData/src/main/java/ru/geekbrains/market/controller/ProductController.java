package ru.geekbrains.market.controller;

import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.service.ProductService;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id).get();
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());
        service.save(newProduct);
        return newProduct;
    }

    @GetMapping("/products/del/{id}")
    public String delete(@PathVariable Long id) {
        String message = service.findById(id).get().toString();
        service.delete(id);
        return "deleted " + message;
    }

    @GetMapping("/products/min/{price}")
    public List<Product> findMin (@PathVariable int price) {
        return service.findMin(price);
    }

    @GetMapping("/products/max/{price}")
    public List<Product> findMax (@PathVariable int price) {
        return service.findMax(price);
    }

    @GetMapping("/products/filter/{min}-{max}")
    public List<Product> betweenPrice (@PathVariable int min, @PathVariable int max) {
        return service.betweenPrice(min,max);
    }

}
