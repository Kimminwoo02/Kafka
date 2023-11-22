package com.example.communicationserver.restTemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SampleResponse {
    private Long id;
    private String username;
    private String password;
    private String name;
}
