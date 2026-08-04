package com.nisum.ecommerce.service;

import com.nisum.ecommerce.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO getProductById(String productId);

    List<ProductDTO> getAllProducts();

    void deleteProduct(String productId);
}
