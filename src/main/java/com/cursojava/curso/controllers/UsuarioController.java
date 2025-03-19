package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.services.UsuarioService;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getListaUsuarios(@RequestHeader(value = "Authorization") String token){

        if (!usuarioService.validarToken(token)){
            return null;

        }

        return usuarioService.getUsuarios();
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void crearUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword() );
        usuario.setPassword(hash);

         usuarioService.agregarUsuario(usuario);
    }

    @RequestMapping(value = "usuario234")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moi");
        usuario.setEmail("lucasmoy@hotmail.com");
        usuario.setTelefono("23424232423");

        return usuario;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){
        if (!usuarioService.validarToken(token)){
            return ;

        }
        usuarioService.borrarUsuario(id);
    }

    @RequestMapping(value = "usuario433")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moi");
        usuario.setEmail("lucasmoy@hotmail.com");
        usuario.setTelefono("23424232423");

        return usuario;
    }
}
