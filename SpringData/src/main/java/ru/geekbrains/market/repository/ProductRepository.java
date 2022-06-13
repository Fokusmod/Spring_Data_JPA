package ru.geekbrains.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.market.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price <= :price")
    List<Product> findMin (int price);

    @Query("select p from Product p where p.price >= :price")
    List<Product> findMax (int price);

    List<Product> findByPriceBetween (int min, int max);
}
