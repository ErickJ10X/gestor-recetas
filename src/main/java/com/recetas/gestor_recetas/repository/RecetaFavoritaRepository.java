package com.recetas.gestor_recetas.repository;

import com.recetas.gestor_recetas.model.Receta;
import com.recetas.gestor_recetas.model.RecetaFavorita;
import com.recetas.gestor_recetas.model.RecetaFavoritaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaFavoritaRepository extends JpaRepository<RecetaFavorita, RecetaFavoritaId> {
    List<RecetaFavorita> findByUsuarioId(Long usuarioId);
    List<RecetaFavorita> findByRecetaId(Long recetaId);
    boolean existsByUsuarioIdAndRecetaId(Long usuarioId, Long recetaId);
    void deleteByUsuarioIdAndRecetaId(Long usuarioId, Long recetaId);
    void deleteByReceta(Receta recetaExistente);
}
