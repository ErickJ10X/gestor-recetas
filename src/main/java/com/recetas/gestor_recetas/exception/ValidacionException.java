package com.recetas.gestor_recetas.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class ValidacionException extends  RuntimeException{
    private final List<FieldError> fieldErrors;

    public ValidacionException(List<FieldError> fieldErrors){
        super("error de validación");
        this.fieldErrors = fieldErrors;
    }

    public ValidacionException(String mensaje){
        super(mensaje);
        this.fieldErrors = null;
    }
}
