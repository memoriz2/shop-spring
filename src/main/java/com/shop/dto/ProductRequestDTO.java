package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private String productName;
    private String thumbnail;
    
    @JsonProperty("thumbnailName")
    private String thumbnailName;
    
    private String description;
    private int price;
    private int stock;
    
    @JsonProperty("productPhoto")
    private List<PhotoInfoDTO> productPhoto;
}
