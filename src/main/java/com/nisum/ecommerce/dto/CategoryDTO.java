package com.nisum.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category payload")
public class CategoryDTO {

    @NotNull(message = "categoryId is required")
    @Schema(description = "category Id")
    private Long categoryId;

    @NotBlank(message = "categoryName is required")
    @Schema(example = "Mobile Phones")
    private String categoryName;

    @Schema(description = "Id of the parent category; null/blank for a root category", example = "6640a1b2c3d4e5f6a7b8c9d0")
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

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public static CategoryDTOBuilder builder() {
        return new CategoryDTOBuilder();
    }

    public static class CategoryDTOBuilder {
        private Long categoryId;
        private String categoryName;
        private String parentCategoryId;

        CategoryDTOBuilder() {
        }

        public CategoryDTOBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public CategoryDTOBuilder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public CategoryDTOBuilder parentCategoryId(String parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(categoryId, categoryName, parentCategoryId);
        }
    }
}

