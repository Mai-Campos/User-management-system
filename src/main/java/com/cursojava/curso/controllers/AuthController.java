package com.cursojava.curso.controllers;

import com.cursojava.curso.models.User;
import com.cursojava.curso.services.UserService;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User loggedUser = userService.verifyCredentials(user);
        if(loggedUser != null){

          String tokenJwt =  jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJwt;
        }

        return "Fail";

    }
}
