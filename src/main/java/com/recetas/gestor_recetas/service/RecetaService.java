package com.recetas.gestor_recetas.service;

import com.recetas.gestor_recetas.dto.RecetaDTO;
import com.recetas.gestor_recetas.exception.RecetaNoEncontradaException;
import com.recetas.gestor_recetas.exception.ValidacionException;
import com.recetas.gestor_recetas.model.Receta;
import com.recetas.gestor_recetas.model.Usuario;
import com.recetas.gestor_recetas.repository.RecetaFavoritaRepository;
import com.recetas.gestor_recetas.repository.RecetaRepository;
import com.recetas.gestor_recetas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {
    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecetaFavoritaRepository recetaFavoritaRepository;

    public List<Receta> obtenerRecetas() {
        return recetaRepository.findAll();
    }

    public Receta crearReceta(Receta receta, Long autorId) {
        if(receta.getIngredientes().split(",").length < 3) {
            throw new ValidacionException("Debe incluir al menos 3 ingredientes");
        }
        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new ValidacionException("Autor no encontrado"));
        receta.setAutor(autor);

        return recetaRepository.save(receta);
    }

    public Receta actualizarReceta(Long id, Receta recetaActualizada) {
        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        recetaExistente.setTitulo(recetaActualizada.getTitulo());
        recetaExistente.setIngredientes(recetaActualizada.getIngredientes());
        recetaExistente.setPasos(recetaActualizada.getPasos());
        recetaExistente.setImagenUrl(recetaActualizada.getImagenUrl());

        return recetaRepository.save(recetaExistente);
    }

    public Receta buscarPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));
    }

    public void eliminarReceta(Long id) {
        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        recetaRepository.delete(recetaExistente);
        recetaFavoritaRepository.deleteByReceta(recetaExistente);
    }

    public List<Receta> buscarRecetas(String titulo, Long autorId) {
        if (titulo != null && !titulo.isEmpty()) {
            return recetaRepository.findByTituloContainingIgnoreCase(titulo);
        } else if (autorId != null) {
            return recetaRepository.findByAutorId(autorId);
        } else {
            return recetaRepository.findAll();
        }
    }

    public void agregarRecetaFavorita(Long recetaId, String emailUsuario) {
        var usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        var receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        var favorita = new com.recetas.gestor_recetas.model.RecetaFavorita();
        favorita.setUsuario(usuario);
        favorita.setReceta(receta);

        recetaFavoritaRepository.save(favorita);
    }

    public void quitarRecetaFavorita(Long recetaId, String emailUsuario) {
        var usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        var receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        recetaFavoritaRepository.deleteByUsuarioIdAndRecetaId(usuario.getId(), receta.getId());
    }
}
