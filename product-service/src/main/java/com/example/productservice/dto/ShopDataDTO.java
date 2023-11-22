package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
//조회된 json형식의 데이터를 모델링한 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDataDTO {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    private List<ItemDTO> items =new ArrayList<>();
}
