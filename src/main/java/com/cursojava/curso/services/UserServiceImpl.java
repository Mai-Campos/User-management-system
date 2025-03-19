package com.cursojava.curso.services;

import com.cursojava.curso.models.User;
import com.cursojava.curso.repository.UserRepository;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User verifyCredentials(User user) {

        User userFinded = userRepository.findByEmail(user.getEmail());

        if (userFinded != null) {

            String passwordHashed = userFinded.getPassword();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (argon2.verify(passwordHashed, user.getPassword())) {
                return userFinded;

            }

            return null;

        }

        return null;
    }

    @Override
    public boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        if (userId == null) {
            return false;
        }

        return true;
    }

    @Override
    public void editUser(User user) {
        User userFinded = userRepository.findByEmail(user.getEmail());
        userFinded.setName(user.getName());
        userFinded.setLastName(user.getLastName());
        userFinded.setCelphone(user.getCelphone());
        userFinded.setPassword(user.getPassword());
        userRepository.save(userFinded);
    }
}

