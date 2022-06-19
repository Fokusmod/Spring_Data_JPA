package ru.geekbrains.market.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.service.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "i", defaultValue = "1") int pageIndex) {
        return service.findCatalog(pageIndex - 1, 10);
    }

    @GetMapping("/show_products")
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



//    @GetMapping("/{id}")
//    public Product findById(@PathVariable Long id) {
//        return service.findById(id).get();
//    }
//
//    @PostMapping()
//    public Product create(@RequestBody Product product) {
//        Product newProduct = new Product();
//        newProduct.setId(product.getId());
//        newProduct.setTitle(product.getTitle());
//        newProduct.setPrice(product.getPrice());
//        service.save(newProduct);
//        return newProduct;
//    }
//
//
//
//    @GetMapping("/min/{price}")
//    public List<Product> findMin(@PathVariable int price) {
//        return service.findMin(price);
//    }
//
//    @GetMapping("/max/{price}")
//    public List<Product> findMax(@PathVariable int price) {
//        return service.findMax(price);
//    }
//
//    @GetMapping("/filter/{min}-{max}")
//    public List<Product> betweenPrice(@PathVariable int min, @PathVariable int max) {
//        return service.betweenPrice(min, max);
//    }

}
