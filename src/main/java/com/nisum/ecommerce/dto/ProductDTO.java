package com.nisum.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product payload")
public class ProductDTO {

    @Schema(description = "Mongo-generated id, ignored on create", accessMode = Schema.AccessMode.READ_ONLY)
    private String productId;

    @NotBlank(message = "productSku is required")
    @Schema(example = "SKU-TSHIRT-BLU-M")
    private String productSku;

    @NotBlank(message = "productName is required")
    @Schema(example = "Men's Cotton Crew-Neck T-Shirt")
    private String productName;

    @Positive(message = "productPrice must be greater than 0")
    @Schema(example = "499.00")
    private Double productPrice;

    @Schema(example = "Cotton T-Shirt")
    private String productShortName;

    @Schema(example = "100% cotton, regular fit, machine washable.")
    private String productDescription;

    @Schema(description = "Server-populated on create; ignored if sent by client", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Schema(example = "3-5 business days")
    private String deliveryTimeSpan;

    @NotNull(message = "categoryId is required")
    @Schema(example = "2")
    private Long categoryId;

    @Schema(example = "https://cdn.example.com/images/products/tshirt-blue.jpg")
    private String productImageUrl;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeliveryTimeSpan() {
        return deliveryTimeSpan;
    }

    public void setDeliveryTimeSpan(String deliveryTimeSpan) {
        this.deliveryTimeSpan = deliveryTimeSpan;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public static ProductDTOBuilder builder() {
        return new ProductDTOBuilder();
    }

    public static class ProductDTOBuilder {
        private String productId;
        private String productSku;
        private String productName;
        private Double productPrice;
        private String productShortName;
        private String productDescription;
        private LocalDateTime createdDate;
        private String deliveryTimeSpan;
        private Long categoryId;
        private String productImageUrl;

        ProductDTOBuilder() {
        }

        public ProductDTOBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public ProductDTOBuilder productSku(String productSku) {
            this.productSku = productSku;
            return this;
        }

        public ProductDTOBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductDTOBuilder productPrice(Double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public ProductDTOBuilder productShortName(String productShortName) {
            this.productShortName = productShortName;
            return this;
        }

        public ProductDTOBuilder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public ProductDTOBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ProductDTOBuilder deliveryTimeSpan(String deliveryTimeSpan) {
            this.deliveryTimeSpan = deliveryTimeSpan;
            return this;
        }

        public ProductDTOBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ProductDTOBuilder productImageUrl(String productImageUrl) {
            this.productImageUrl = productImageUrl;
            return this;
        }

        public ProductDTO build() {
            return new ProductDTO(productId, productSku, productName, productPrice, productShortName,
                    productDescription, createdDate, deliveryTimeSpan, categoryId, productImageUrl);
        }
    }
}
