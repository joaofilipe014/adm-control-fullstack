package com.admControl.adm_control_api.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
    Integer status,
    String message,
    LocalDateTime timestamp
) {
    
}
