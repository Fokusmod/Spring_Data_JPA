package ru.geekbrains.market.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.model.Cart;
import ru.geekbrains.market.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private Cart cart;

    private List<Product> products;

    @Autowired
    public CartService(Cart cart) {
        this.cart = cart;
        this.products = cart.getProducts();
    }


    public void addToCart(Product product) {
        products.add(product);
    }

    public void deleteToCart(String title) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTitle().equals(title)) {
                products.remove(i);
                break;
            }
        }
    }

    public void checkout() {

    }

    public void clearCart() {

    }

    public List<CartDto> showCart() {
        List<CartDto> cartDtoList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            cartDtoList.add(new CartDto(products.get(i).getTitle(), products.get(i).getPrice()));
        }
        return cartDtoList;
    }

}
