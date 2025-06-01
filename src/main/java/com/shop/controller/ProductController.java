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
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
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
    }

    // 헬스 체크용 엔드포인트
    @GetMapping
    public ResponseEntity<String> healthCheck() {
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
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    // 모든 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(productId, productDTO));
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    // 상품명으로 검색
    @GetMapping("/search/name/{productName}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String productName){
        return ResponseEntity.ok(productService.getProductByName(productName));
    }

    // 가격 범위로 검색
    @GetMapping("/search/price")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPriceRange(@RequestParam int minPrice, @RequestParam int maxPrice){
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    // 재고가 있는 상품만 검색
    @GetMapping("/search/stock")
    public ResponseEntity<List<ProductResponseDTO>> getProductInStock(){
        return ResponseEntity.ok(productService.getProductsInStock());
    }

    // 상품명으로 검색(부분 일치)
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }
}
