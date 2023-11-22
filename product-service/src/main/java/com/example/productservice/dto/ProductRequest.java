package com.example.productservice.dto;

import com.example.productservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String productName;
    private String info;
    private int price;
    private String image;
    private Category category;
}
