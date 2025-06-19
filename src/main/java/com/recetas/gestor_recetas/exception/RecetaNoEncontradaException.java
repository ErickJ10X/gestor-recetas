package com.recetas.gestor_recetas.exception;

public class RecetaNoEncontradaException extends  RuntimeException{
    public RecetaNoEncontradaException(Long id) {
        super("Receta con Id " + id + " no encontrada");
    }
}
