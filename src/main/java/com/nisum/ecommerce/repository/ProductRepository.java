package com.nisum.ecommerce.repository;

import com.nisum.ecommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryId(Long categoryId);

    Optional<Product> findByProductSku(String productSku);

    boolean existsByProductSku(String productSku);
}
