package com.shop.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.dto.ProductRequestDTO;
import com.shop.dto.ProductResponseDTO;
import com.shop.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {
    "https://shop-tiqaktmxj.vercel.app",
    "https://shop-tiqaktmxj-dfp3ekhhx-memoriz2s-projects.vercel.app"
})
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
        logger.info("ProductController initialized");
    }

    // 헬스 체크용 엔드포인트
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check endpoint called");
        return ResponseEntity.ok("API is running");
    }

    // 상품 등록
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productDTO){
        logger.info("Received product creation request: {}", productDTO);
        try {
            ProductResponseDTO response = productService.createProduct(productDTO);
            logger.info("Product created successfully: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error creating product: ", e);
            throw e;
        }
    }

    // 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id){
        logger.info("Getting product with id: {}", id);
        return ResponseEntity.ok(productService.getProduct(id));
    }

    // 모든 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        logger.info("Getting all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDTO){
        logger.info("Updating product with id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        logger.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    // 상품명으로 검색
    @GetMapping("/search/name/{productName}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String productName){
        logger.info("Searching product by name: {}", productName);
        return ResponseEntity.ok(productService.getProductByName(productName));
    }

    // 가격 범위로 검색
    @GetMapping("/search/price")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPriceRange(
            @RequestParam int minPrice, 
            @RequestParam int maxPrice){
        logger.info("Searching products by price range: {} - {}", minPrice, maxPrice);
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    // 재고가 있는 상품만 검색
    @GetMapping("/search/stock")
    public ResponseEntity<List<ProductResponseDTO>> getProductInStock(){
        logger.info("Getting products in stock");
        return ResponseEntity.ok(productService.getProductsInStock());
    }

    // 상품명으로 검색(부분 일치)
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String keyword){
        logger.info("Searching products with keyword: {}", keyword);
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }
}
