package com.recetas.gestor_recetas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDTO {
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;
    @NotBlank(message = "Los ingredientes son obligatorios")
    @Size(min = 10, message = "Describe al menos 3 ingredientes")
    private String ingredientes;
    @NotBlank
    @Size(min = 5, max = 100)
    private String pasos;
    private String imagenUrl;
    @NotNull(message = "El autor es requerido")
    private Long autorId;
}
