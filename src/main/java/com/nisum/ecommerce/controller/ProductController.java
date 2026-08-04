package com.nisum.ecommerce.controller;

import com.nisum.ecommerce.dto.ProductDTO;
import com.nisum.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Endpoints for managing catalog products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Save (create) a new product")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Validation failure")
    @ApiResponse(responseCode = "404", description = "Referenced categoryId does not exist")
    @ApiResponse(responseCode = "409", description = "Product with the given SKU already exists")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO saved = productService.saveProduct(productDTO);
        return ResponseEntity
                .created(URI.create("/api/v1/products/" + saved.getProductId()))
                .body(saved);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "List of products returned successfully")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get a single product by id")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductDTO> getProductById(
            @Parameter(description = "Mongo id of the product") @PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product by id")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Mongo id of the product") @PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
