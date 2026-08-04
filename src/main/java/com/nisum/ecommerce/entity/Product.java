package com.nisum.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String productId;

    @Indexed(unique = true)
    private String productSku;

    private String productName;

    private Double productPrice;

    private String productShortName;

    private String productDescription;

    private LocalDateTime createdDate;

    private String deliveryTimeSpan;

    /**
     * References Category#categoryId. Kept as a plain field (not @DBRef)
     * to avoid eager-fetch penalties on the read-heavy getAll path;
     * resolve via CategoryService when the caller actually needs category details.
     */
    @Indexed
    private Long categoryId;

    private String productImageUrl;

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

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
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

        ProductBuilder() {
        }

        public ProductBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public ProductBuilder productSku(String productSku) {
            this.productSku = productSku;
            return this;
        }

        public ProductBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder productPrice(Double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public ProductBuilder productShortName(String productShortName) {
            this.productShortName = productShortName;
            return this;
        }

        public ProductBuilder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public ProductBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ProductBuilder deliveryTimeSpan(String deliveryTimeSpan) {
            this.deliveryTimeSpan = deliveryTimeSpan;
            return this;
        }

        public ProductBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ProductBuilder productImageUrl(String productImageUrl) {
            this.productImageUrl = productImageUrl;
            return this;
        }

        public Product build() {
            return new Product(productId, productSku, productName, productPrice, productShortName, productDescription, createdDate, deliveryTimeSpan, categoryId, productImageUrl);
        }
    }
}
