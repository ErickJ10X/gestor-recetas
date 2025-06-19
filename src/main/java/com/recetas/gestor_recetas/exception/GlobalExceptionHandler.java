package com.recetas.gestor_recetas.exception;

import com.recetas.gestor_recetas.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorDTO error = new ErrorDTO(
                "Validación fallida",
                LocalDateTime.now(),
                detalles
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Maneja nuestras excepciones personalizadas
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ErrorDTO> handleValidacionException(ValidacionException ex) {
        List<String> detalles;
        if (ex.getFieldErrors() != null) {
            detalles = ex.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
        } else {
            detalles = List.of(ex.getMessage());
        }
        ErrorDTO error = new ErrorDTO(
                "Error de validación",
                LocalDateTime.now(),
                detalles
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecetaNoEncontradaException.class)
    public ResponseEntity<ErrorDTO> handleRecetaNoEncontrada(RecetaNoEncontradaException ex) {
        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                LocalDateTime.now(),
                List.of("Verifica el ID proporcionado")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
