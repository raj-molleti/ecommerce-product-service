package com.nisum.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Category document - supports hierarchical categories via parentCategoryId.
 * A null/blank parentCategoryId denotes a root-level category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id; // MongoDB's internal ObjectId — hidden from API

    @Indexed(unique = true)
    private Long categoryId; // numeric, starts at 1, business-facing

    @Indexed
    private String categoryName;

    /**
     * Self-referencing id pointing to the parent Category.
     * Null for top-level/root categories.
     */
    private String parentCategoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public static class CategoryBuilder {
        private String id;
        private Long categoryId;
        private String categoryName;
        private String parentCategoryId;

        CategoryBuilder() {
        }

        public CategoryBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public CategoryBuilder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public CategoryBuilder parentCategoryId(String parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
            return this;
        }

        public Category build() {
            return new Category(id, categoryId, categoryName, parentCategoryId);
        }
    }
}
