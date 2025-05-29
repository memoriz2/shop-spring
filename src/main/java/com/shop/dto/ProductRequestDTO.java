package com.shop.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String productName;
    private String description;
    private int price;
    private int stock;
    private String productPhoto;
}
