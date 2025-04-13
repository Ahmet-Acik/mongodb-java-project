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

    @Test
    public void testGetUserById() {
        UserRepository userRepository = new UserRepository();

        // Insert a user and retrieve the generated ID
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        userRepository.insertUser(user);

        // Fetch the user by ID
        String userId = user.getId(); // Assuming `insertUser` sets the ID on the user object
        User retrievedUser = userRepository.getUserById(userId);

        // Assertions
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getName()).isEqualTo("John Doe");
        assertThat(retrievedUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testGetUserByInvalidId() {
        UserRepository userRepository = new UserRepository();

        // Attempt to retrieve a user with an invalid ID
        String invalidId = "invalid_id";
        User retrievedUser = userRepository.getUserById(invalidId);

        // Assertions
        assertThat(retrievedUser).isNull();
    }

}