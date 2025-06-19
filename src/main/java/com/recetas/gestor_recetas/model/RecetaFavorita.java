package com.recetas.gestor_recetas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "receta_favorita")
@IdClass(RecetaFavoritaId.class)
public class RecetaFavorita {
    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Id
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
