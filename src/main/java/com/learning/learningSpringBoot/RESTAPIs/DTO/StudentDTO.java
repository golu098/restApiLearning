package com.learning.learningSpringBoot.RESTAPIs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   // Needed for ModelMapper
@AllArgsConstructor  // Convenient for manually creating DTOs
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
}
