package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//상품1개를 표현한 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String title;
    private String link;
    private String image;
    private int lprice;
    private String mallName;
    private String brand;

}
