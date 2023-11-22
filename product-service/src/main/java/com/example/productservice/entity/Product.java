package com.example.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@DynamicInsert
public class Product {
    @Id
    @GeneratedValue
    private Long productNo;
    private String productName;
    private String info;
    private int price;
    private String image;
    @ColumnDefault("20")
    private Integer stock;//재고량
    private LocalDateTime insertTime;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String productName, String info, int price, String image, Category category) {
        this.productName = productName;
        this.info = info;
        this.price = price;
        this.image = image;
        this.category = category;
    }
}
