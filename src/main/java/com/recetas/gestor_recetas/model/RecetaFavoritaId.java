package com.recetas.gestor_recetas.model;

import java.io.Serializable;
import java.util.Objects;

public class RecetaFavoritaId implements Serializable {
    private Long usuario;
    private Long receta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecetaFavoritaId that)) return false;
        return Objects.equals(usuario, that.usuario) && Objects.equals(receta, that.receta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, receta);
    }
}
