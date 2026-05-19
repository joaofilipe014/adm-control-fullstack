package com.admControl.adm_control_api.department.dto;

import jakarta.validation.constraints.*;

public record DepartmentRequestDTO(
    
    @NotBlank(message = "Department name is required")
    @Size(min = 3, max = 100, message = "Name must have between 3 and 100 characters")
    String name,

    @Size(max = 255, message = "Description must have a maximum of 255 characters")
    String description
) {
    
}
