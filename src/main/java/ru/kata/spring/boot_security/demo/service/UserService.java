package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    List<User> getAllUser();
    void updateUser(User user);
    void addUser(User user);
    User findUserById(Long id);
    UserDetails loadUserByUsername(String username);
    void deleteUserById(Long id);
}