package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhotoInfoDTO {
    private String url;
    
    @JsonProperty("filename")
    private String filename;
} 