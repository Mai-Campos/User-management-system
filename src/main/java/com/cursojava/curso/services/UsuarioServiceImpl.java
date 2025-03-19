package com.cursojava.curso.services;

import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.repository.UsuarioRepository;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
   private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void borrarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
       usuarioRepository.save(usuario);

    }

    @Override
    public Usuario verificarCrendenciales(Usuario usuario) {

       Usuario usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());

       if (usuarioEncontrado != null){

           String passwordHashed = usuarioEncontrado.getPassword();
           Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
           if(argon2.verify(passwordHashed,usuario.getPassword())){
               return  usuarioEncontrado;

           }

           return null;

       }

        return null;
    }

    @Override
    public boolean validarToken(String token) {
        String usuarioId =  jwtUtil.getKey(token);
        if(usuarioId == null){
            return false;
        }

        return true;
    }
}
