package com.nisum.ecommerce.service;

import com.nisum.ecommerce.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long categoryId);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(Long categoryId);
}
