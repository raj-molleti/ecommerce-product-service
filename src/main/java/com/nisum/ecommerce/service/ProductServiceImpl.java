package com.nisum.ecommerce.service;

import com.nisum.ecommerce.dto.ProductDTO;
import com.nisum.ecommerce.entity.Product;
import com.nisum.ecommerce.exception.DuplicateResourceException;
import com.nisum.ecommerce.exception.ResourceNotFoundException;
import com.nisum.ecommerce.repository.CategoryRepository;
import com.nisum.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        if (!categoryRepository.existsByCategoryId(productDTO.getCategoryId())) {
            throw new ResourceNotFoundException("Category not found with id: " + productDTO.getCategoryId());
        }
        if (productRepository.existsByProductSku(productDTO.getProductSku())) {
            throw new DuplicateResourceException(
                    "Product already exists with SKU: " + productDTO.getProductSku());
        }

        Product product = Product.builder()
                .productSku(productDTO.getProductSku())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice())
                .productShortName(productDTO.getProductShortName())
                .productDescription(productDTO.getProductDescription())
                .createdDate(LocalDateTime.now())
                .deliveryTimeSpan(productDTO.getDeliveryTimeSpan())
                .categoryId(productDTO.getCategoryId())
                .productImageUrl(productDTO.getProductImageUrl())
                .build();

        Product saved = productRepository.save(product);
        log.info("Saved product [{}] with id [{}]", saved.getProductSku(), saved.getProductId());
        return toDTO(saved);
    }




    @Override
    public ProductDTO getProductById(String productId) {
        return productRepository.findById(productId)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void deleteProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
        log.info("Deleted product with id [{}]", productId);
    }

    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .productSku(product.getProductSku())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productShortName(product.getProductShortName())
                .productDescription(product.getProductDescription())
                .createdDate(product.getCreatedDate())
                .deliveryTimeSpan(product.getDeliveryTimeSpan())
                .categoryId(product.getCategoryId())
                .productImageUrl(product.getProductImageUrl())
                .build();
    }
}
