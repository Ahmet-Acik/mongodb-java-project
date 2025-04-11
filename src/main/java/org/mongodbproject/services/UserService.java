package org.mongodbproject.services;

import org.mongodbproject.models.User;
import org.mongodbproject.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void addUser(User user) {
        userRepository.insertUser(user);
    }

    public List<User> listUsers() {
        return userRepository.getAllUsers();
    }
}