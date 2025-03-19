package com.cursojava.curso.services;

import com.cursojava.curso.models.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    void deleteUser(Long id);
    void createUser(User user);
    User verifyCredentials(User user);
    boolean validateToken(String token);
    void editUser( User user);
}
