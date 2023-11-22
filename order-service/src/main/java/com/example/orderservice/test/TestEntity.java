package com.example.orderservice.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//사용자정의 시퀀스적용
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "my_generator",
        sequenceName = "test_sq",
        allocationSize = 1

)
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_generator")
    private Long id;
    private String info;

    public TestEntity(String info) {
        this.info = info;
    }
}
