package ru.geekbrains.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.exception.ResourceNotFoundException;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.service.CartService;
import ru.geekbrains.market.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;


    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto> showCart (){
        return cartService.showCart();
    }

    @PostMapping("/cart/{id}")
    public CartDto addProduct (@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("This product not found"));
        cartService.addToCart(product);
        return new CartDto(product.getTitle(),product.getPrice());
    }

    @DeleteMapping("/cart/{title}")
    public void deleteProduct (@PathVariable String title) {
        cartService.deleteToCart(title);
    }


}
