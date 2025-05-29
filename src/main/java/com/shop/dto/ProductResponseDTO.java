package com.shop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String description;
    private int price;
    private int stock;
    private String productPhoto;
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일
}
