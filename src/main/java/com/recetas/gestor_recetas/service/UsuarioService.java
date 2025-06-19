package com.recetas.gestor_recetas.service;

import com.recetas.gestor_recetas.model.Usuario;
import com.recetas.gestor_recetas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }
        usuarioExistente.setRol(usuarioActualizado.getRol());

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuarioRepository.delete(usuarioExistente);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
