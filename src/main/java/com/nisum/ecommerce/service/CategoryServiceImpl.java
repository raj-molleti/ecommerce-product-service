package com.nisum.ecommerce.service;

import com.nisum.ecommerce.dto.CategoryDTO;
import com.nisum.ecommerce.entity.Category;
import com.nisum.ecommerce.exception.DuplicateResourceException;
import com.nisum.ecommerce.exception.ResourceNotFoundException;
import com.nisum.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryDTO.getCategoryName())) {
            throw new DuplicateResourceException(
                    "Category already exists with name: " + categoryDTO.getCategoryName());
        }

        Category category = Category.builder()
                .categoryId(categoryDTO.getCategoryId())
                .categoryName(categoryDTO.getCategoryName())
                .parentCategoryId(categoryDTO.getParentCategoryId())
                .build();

        Category saved = categoryRepository.save(category);
        log.info("Saved category [{}] with id [{}]", saved.getCategoryName(), saved.getCategoryId());
        return toDTO(saved);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId)
                .map(this::toDTO)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
        log.info("Deleted category with id [{}]", categoryId);
    }

    private CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .parentCategoryId(category.getParentCategoryId())
                .build();
    }
}
