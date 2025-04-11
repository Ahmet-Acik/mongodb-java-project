package org.mongodbproject.integration;

import org.mongodbproject.models.User;
import org.mongodbproject.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    @Test
    public void testInsertAndRetrieveUser() {
        UserRepository userRepository = new UserRepository();

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userRepository.insertUser(user);

        List<User> users = userRepository.getAllUsers();
        assertThat(users).isNotEmpty();
    }
    @Test
    public void testGetAllUsers() {
        UserRepository userRepository = new UserRepository();

        List<User> users = userRepository.getAllUsers();
        assertThat(users).isNotEmpty();
    }

}