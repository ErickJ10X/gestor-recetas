package com.recetas.gestor_recetas.repository;

import com.recetas.gestor_recetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {
    List<Receta> findByAutorId(Long autorId);
    List<Receta> findByTituloContainingIgnoreCase(String titulo);
}
