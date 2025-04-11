package org.mongodbproject.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodbproject.config.MongoDBConnection;
import org.mongodbproject.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final MongoCollection<Document> collection;

    public UserRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("users");
        logger.info("Connected to MongoDB collection: users");
    }

    public void insertUser(User user) {
        try {
            logger.info("Inserting user: {}", user);
            collection.insertOne(user.toDocument());
            logger.info("User inserted successfully: {}", user);
        } catch (Exception e) {
            logger.error("Error inserting user: {}", e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            logger.info("Retrieving all users from the database");
            for (Document doc : collection.find()) {
                users.add(User.fromDocument(doc));
            }
            logger.info("Retrieved {} users from the database", users.size());
        } catch (Exception e) {
            logger.error("Error retrieving users: {}", e.getMessage(), e);
        }
        return users;
    }
}