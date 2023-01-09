package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUser();
    void updateUser(User user);

    void addUser(User user);

    User findUserById(Long id);

    void deleteUserById(Long id);

    User findUserByName(String name);


}