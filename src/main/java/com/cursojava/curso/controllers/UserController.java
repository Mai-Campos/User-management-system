package com.cursojava.curso.controllers;

import com.cursojava.curso.models.User;
import com.cursojava.curso.services.UserService;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/users")
    public List<User> getUsersList(@RequestHeader(value = "Authorization") String token){

        if (!userService.validateToken(token)){
            return null;

        }

        return userService.getUsers();
    }


    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword() );
        user.setPassword(hash);

         userService.createUser(user);
    }

    @RequestMapping(value = "usuario234")
    public void edit(){
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!userService.validateToken(token)){
            return ;

        }
        userService.deleteUser(id);
    }
    

    @RequestMapping(value = "usuario433")
    public User find(){
        User user = new User();
        user.setName("Lucas");
        user.setLastName("Moi");
        user.setEmail("lucasmoy@hotmail.com");
        user.setCelphone("23424232423");

        return user;
    }
}
