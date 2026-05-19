package com.admControl.adm_control_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // intercepta exceções da aplicação inteira
                      // Advice = comportamento aplicado globalmente -> Tratamento global de erros
public class GlobalExceptionHandler {
    // @ExceptionHandler
    // “quando BusinessException acontecer,
    // execute este método”
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> hendleBusinessException(
        BusinessException ex // Recebe a exceção lançada 
                             // ex.getMessage() retorna a mensagem de erro -> Ex: throw new BusinessException("Department already exists"); -> ex.getMessage() -> retorna Department already exists 
    ) {
        ErrorResponse error = new ErrorResponse(
            // o 409 CONFLICT significa -> o recurso entrou em conflito com regra de negócio
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> hendleValidationException(
        MethodArgumentNotValidException ex
    ) {
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse error = new ErrorResponse(
            // 400 BAD_REQUEST significa -> cliente enviou dados inválidos
            HttpStatus.BAD_REQUEST.value(),
            message,
            LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    } 
}
