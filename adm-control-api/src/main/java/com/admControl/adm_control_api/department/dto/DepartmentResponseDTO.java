package com.admControl.adm_control_api.department.dto;

public record DepartmentResponseDTO(
    Long id,
    String name,
    String description,
    Boolean active
) {
    
}
