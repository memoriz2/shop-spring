package com.shop.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    // private final ProductService productService;

    // @Autowired
    // public ProductController(ProductService productService){
    //     this.productService = productService;
    //     logger.info("ProductController initialized with mapping: /api/products");
    // }

    // 헬스 체크용 엔드포인트
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check endpoint called");
        return ResponseEntity.ok("API is running");
    }

    // 상품 등록
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody ProductRequestDTO productDTO){
        logger.info("Received product creation request: {}", productDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product creation endpoint reached");
        response.put("receivedData", productDTO.toString());
        return ResponseEntity.ok(response);
    }

    // 상품 조회
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getProduct(@PathVariable Long id){
        logger.info("Getting product with id: {}", id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Get product endpoint reached");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    // 모든 상품 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getAllProducts(){
        logger.info("Getting all products");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Get all products endpoint reached");
        return ResponseEntity.ok(response);
    }

    // 상품 수정
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDTO){
        logger.info("Updating product with id: {}", id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Update product endpoint reached");
        response.put("id", id.toString());
        response.put("receivedData", productDTO.toString());
        return ResponseEntity.ok(response);
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id){
        logger.info("Deleting product with id: {}", id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Delete product endpoint reached");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    // 상품명으로 검색
    @GetMapping(value = "/search/name/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getProductByName(@PathVariable String productName){
        logger.info("Searching product by name: {}", productName);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Search by name endpoint reached");
        response.put("productName", productName);
        return ResponseEntity.ok(response);
    }

    // 가격 범위로 검색
    @GetMapping(value = "/search/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getProductsByPriceRange(
            @RequestParam int minPrice, 
            @RequestParam int maxPrice){
        logger.info("Searching products by price range: {} - {}", minPrice, maxPrice);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Search by price range endpoint reached");
        response.put("minPrice", String.valueOf(minPrice));
        response.put("maxPrice", String.valueOf(maxPrice));
        return ResponseEntity.ok(response);
    }

    // 재고가 있는 상품만 검색
    @GetMapping(value = "/search/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getProductInStock(){
        logger.info("Getting products in stock");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Search in stock endpoint reached");
        return ResponseEntity.ok(response);
    }

    // 상품명으로 검색(부분 일치)
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> searchProducts(@RequestParam String keyword){
        logger.info("Searching products with keyword: {}", keyword);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Search by keyword endpoint reached");
        response.put("keyword", keyword);
        return ResponseEntity.ok(response);
    }
}
