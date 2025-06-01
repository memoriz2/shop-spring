package com.shop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.dto.ProductRequestDTO;
import com.shop.dto.ProductResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

// @Service
// @Transactional
public class ProductService {
    // private final ProductRepository productRepository;

    // @Autowired
    // public ProductService(ProductRepository productRepository){
    //     this.productRepository = productRepository;
    // }

    // DTO를 엔티티로 변환
    // private Product convertToEntity(ProductRequestDTO dto) {
    //     Product product = new Product();
    //     product.setProductName(dto.getProductName());
    //     product.setDescription(dto.getDescription());
    //     product.setPrice(dto.getPrice());
    //     product.setStock(dto.getStock());
    //     product.setProductPhoto(dto.getProductPhoto());
    //     return product;
    // }

    // 엔티티를 DTO로 변환
    // private ProductResponseDTO convertToDTO(Product product) {
    //     ProductResponseDTO dto = new ProductResponseDTO();
    //     dto.setProductId(product.getProductId());
    //     dto.setProductName(product.getProductName());
    //     dto.setDescription(product.getDescription());
    //     dto.setPrice(product.getPrice());
    //     dto.setStock(product.getStock());
    //     dto.setProductPhoto(product.getProductPhoto());
    //     dto.setCreatedAt(product.getCreatedAt());
    //     dto.setUpdatedAt(product.getUpdatedAt());
    //     return dto;
    // }

    // 상품 등록
    // public ProductResponseDTO createProduct(ProductRequestDTO productDTO){
    //     Product product = convertToEntity(productDTO);
    //     return convertToDTO(productRepository.save(product));
    // }

    // 상품 조회
    // public ProductResponseDTO getProduct(Long productId){
    //     Product product = productRepository.findById(productId)
    //         .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
    //     return convertToDTO(product);
    // }

    // 모든 상품 조회
    // public List<ProductResponseDTO> getAllProducts(){
    //     return productRepository.findAll().stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }

    // 상품 수정
    // public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productDTO){
    //     Product product = productRepository.findById(productId)
    //         .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        
    //     product.setProductName(productDTO.getProductName());
    //     product.setDescription(productDTO.getDescription());
    //     product.setPrice(productDTO.getPrice());
    //     product.setStock(productDTO.getStock());
    //     product.setProductPhoto(productDTO.getProductPhoto());
        
    //     return convertToDTO(productRepository.save(product));
    // }

    // 상품 삭제
    // public void deleteProduct(Long productId){
    //     Product product = productRepository.findById(productId)
    //         .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
    //     productRepository.delete(product);
    // }

    // 상품명으로 검색
    // public ProductResponseDTO getProductByName(String productName){
    //     Product product = productRepository.findByProductName(productName);
    //     if (product == null) {
    //         throw new RuntimeException("상품을 찾을 수 없습니다.");
    //     }
    //     return convertToDTO(product);
    // }

    // 가격 범위로 검색
    // public List<ProductResponseDTO> getProductsByPriceRange(int minPrice, int maxPrice){
    //     return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }

    // 재고가 있는 상품만 검색
    // public List<ProductResponseDTO> getProductsInStock(){
    //     return productRepository.findByStockGreaterThan(0).stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }

    // 상품명으로 검색 (부분 일치)
    // public List<ProductResponseDTO> searchProducts(String keyword){
    //     return productRepository.findByProductNameContaining(keyword).stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
}
