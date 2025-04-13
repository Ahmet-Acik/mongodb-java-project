package org.mongodbproject.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mongodbproject.models.Address;
import org.mongodbproject.models.User;
import org.mongodbproject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        // Clear the database before each test
        userRepository.getAllUsers().forEach(user -> userRepository.deleteUserById(user.getId()));
    }

    @Test
    public void shouldInsertAndRetrieveUser() {
        User user = createTestUser("John Doe", "john.doe@example.com");
        userRepository.insertUser(user);

        List<User> users = userRepository.getAllUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getName()).isEqualTo("John Doe");
    }

    @Test
    public void shouldRetrieveAllUsers() {
        userRepository.insertUser(createTestUser("User1", "user1@example.com"));
        userRepository.insertUser(createTestUser("User2", "user2@example.com"));

        List<User> users = userRepository.getAllUsers();
        assertThat(users).hasSize(2);
    }

    @Test
    public void shouldRetrieveUserById() {
        User user = createTestUser("John Doe", "john.doe@example.com");
        userRepository.insertUser(user);

        User retrievedUser = userRepository.getUserById(user.getId());
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getName()).isEqualTo("John Doe");
    }

    @Test
    public void shouldReturnNullForInvalidId() {
        User retrievedUser = userRepository.getUserById("invalid_id");
        assertThat(retrievedUser).isNull();
    }

    @Test
    public void shouldInsertUserWithAddressAndHobbies() {
        User user = createTestUser("Jane Doe", "jane.doe@example.com");
        user.setAddress(createTestAddress());
        user.setHobbies(List.of("Reading", "Traveling"));

        userRepository.insertUser(user);

        User retrievedUser = userRepository.getUserById(user.getId());
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getAddress().getCity()).isEqualTo("Springfield");
        assertThat(retrievedUser.getHobbies()).containsExactly("Reading", "Traveling");
    }

    @Test
    public void shouldReturnEmptyListWhenNoUsersExist() {
        List<User> users = userRepository.getAllUsers();
        assertThat(users).isEmpty();
    }

    @Test
    public void shouldUpdateUserDetails() {
        User user = createTestUser("John Smith", "john.smith@example.com");
        userRepository.insertUser(user);

        user.setName("John Updated");
        user.setEmail("john.updated@example.com");
        userRepository.insertUser(user);

        User updatedUser = userRepository.getUserById(user.getId());
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo("John Updated");
    }

    @Test
    public void shouldDeleteUser() {
        User user = createTestUser("Alice", "alice@example.com");
        userRepository.insertUser(user);

        userRepository.getAllUsers().remove(user);

        User deletedUser = userRepository.getUserById(user.getId());
        assertThat(deletedUser).isNull();
    }

    private User createTestUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    private Address createTestAddress() {
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setZipCode("12345");
        return address;
    }
}