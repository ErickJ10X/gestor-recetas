package com.recetas.gestor_recetas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String ingredientes;

    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String pasos;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;
}
