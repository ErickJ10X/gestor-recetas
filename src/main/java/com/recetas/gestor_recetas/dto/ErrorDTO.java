package com.recetas.gestor_recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String mensaje;
    private LocalDateTime timestamp;
    private List<String> detalles;
}
