package ru.geekbrains.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

    public List<Product> findMin (int price) {
        return repository.findMin(price);
    }

    public List<Product> findMax (int price) {
        return repository.findMax(price);
    }

    public List<Product> betweenPrice (int min, int max){
        return repository.findByPriceBetween(min,max);
    }
}
