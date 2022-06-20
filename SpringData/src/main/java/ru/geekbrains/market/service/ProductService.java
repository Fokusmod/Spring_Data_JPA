package ru.geekbrains.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.exception.ResourceNotFoundException;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll (){
        return repository.findAll();
    }

    public Page<Product> findCatalog (int pageIndex, int pageSize){
        return repository.findAll(PageRequest.of(pageIndex,pageSize));
    }

    public Product change (Product change_product) {
        Product product = repository.findByTitle(change_product.getTitle()).orElseThrow(()->
                new ResourceNotFoundException("This Product " + change_product.getTitle() + " not found"));
        product.setPrice(change_product.getPrice());
        return repository.save(product);
    }

    public Optional<Product> findByTitle (String title){
        return repository.findByTitle(title);
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
