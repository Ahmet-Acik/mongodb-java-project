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
        logger.info("Inserting user: {}", user);
        collection.insertOne(user.toDocument());
        logger.info("User inserted successfully: {}", user);
    }

    public List<User> getAllUsers() {
        logger.info("Retrieving all users from the database");
        List<User> users = new ArrayList<>();
        for (Document doc : collection.find()) {
            users.add(User.fromDocument(doc));
        }
        logger.info("Retrieved {} users from the database", users.size());
        return users;
    }
}