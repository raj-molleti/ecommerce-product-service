package com.nisum.ecommerce.repository;

import com.nisum.ecommerce.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByParentCategoryId(String parentCategoryId);

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    Stream<Category> findByCategoryId(Long categoryId);

    boolean existsById(Long categoryId);

    void deleteById(Long categoryId);

    boolean existsByCategoryId(Long categoryId);
}
