package com.shop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.dto.ProductRequestDTO;
import com.shop.dto.ProductResponseDTO;
import com.shop.dto.PhotoInfoDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // DTO를 엔티티로 변환
    private Product convertToEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setThumbnail(dto.getThumbnail());
        product.setThumbnailName(dto.getThumbnailName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        
        // PhotoInfoDTO 리스트를 Map으로 변환
        Map<String, String> photoMap = new HashMap<>();
        if (dto.getProductPhoto() != null) {
            dto.getProductPhoto().forEach(photo -> 
                photoMap.put(photo.getUrl(), photo.getFilename())
            );
        }
        product.setProductPhoto(photoMap);
        
        return product;
    }

    // 엔티티를 DTO로 변환
    private ProductResponseDTO convertToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setThumbnail(product.getThumbnail());
        dto.setThumbnailName(product.getThumbnailName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        
        // Map을 PhotoInfoDTO 리스트로 변환
        List<PhotoInfoDTO> photoList = new ArrayList<>();
        if (product.getProductPhoto() != null) {
            product.getProductPhoto().forEach((url, filename) -> {
                PhotoInfoDTO photoInfo = new PhotoInfoDTO();
                photoInfo.setUrl(url);
                photoInfo.setFilename(filename);
                photoList.add(photoInfo);
            });
        }
        dto.setProductPhoto(photoList);
        
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    // 상품 등록
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO){
        Product product = convertToEntity(productDTO);
        return convertToDTO(productRepository.save(product));
    }

    // 상품 조회
    public ProductResponseDTO getProduct(Long productId){
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        return convertToDTO(product);
    }

    // 모든 상품 조회
    public List<ProductResponseDTO> getAllProducts(){
        return productRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // 상품 수정
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productDTO){
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        
        product.setProductName(productDTO.getProductName());
        product.setThumbnail(productDTO.getThumbnail());
        product.setThumbnailName(productDTO.getThumbnailName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        
        // PhotoInfoDTO 리스트를 Map으로 변환
        Map<String, String> photoMap = new HashMap<>();
        if (productDTO.getProductPhoto() != null) {
            productDTO.getProductPhoto().forEach(photo -> 
                photoMap.put(photo.getUrl(), photo.getFilename())
            );
        }
        product.setProductPhoto(photoMap);
        
        return convertToDTO(productRepository.save(product));
    }

    // 상품 삭제
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        productRepository.delete(product);
    }

    // 상품명으로 검색
    public ProductResponseDTO getProductByName(String productName){
        Product product = productRepository.findByProductName(productName);
        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }
        return convertToDTO(product);
    }

    // 가격 범위로 검색
    public List<ProductResponseDTO> getProductsByPriceRange(int minPrice, int maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // 재고가 있는 상품만 검색
    public List<ProductResponseDTO> getProductsInStock(){
        return productRepository.findByStockGreaterThan(0).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // 상품명으로 검색 (부분 일치)
    public List<ProductResponseDTO> searchProducts(String keyword){
        return productRepository.findByProductNameContaining(keyword).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
}
