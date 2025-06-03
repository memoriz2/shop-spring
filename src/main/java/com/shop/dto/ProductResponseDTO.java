package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String thumbnail;
    
    @JsonProperty("thumbnailName")
    private String thumbnailName;
    
    private String description;
    private int price;
    private int stock;
    
    @JsonProperty("productPhoto")
    private List<PhotoInfoDTO> productPhoto;
    
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일
}
