package com.saboresdelmundo.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saboresdelmundo.recetas.model.Usuario;
import com.saboresdelmundo.recetas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    // Simulación de acceso a datos. Reemplazar por repositorio real.
    public Usuario findById(Long id) {
        // TODO: Implementar acceso a base de datos
        return null;
    }

    public Usuario getAuthenticatedUser() {
        // TODO: Implementar obtención de usuario autenticado (Spring Security)
        return null;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
