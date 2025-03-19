package com.cursojava.curso.repository;

import com.cursojava.curso.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
