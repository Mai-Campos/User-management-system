package com.cursojava.curso.services;

import com.cursojava.curso.models.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    void borrarUsuario(Long id);
    void agregarUsuario(Usuario usuario);
    Usuario verificarCrendenciales(Usuario usuario);
    boolean validarToken(String token);
}
