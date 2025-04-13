package org.mongodbproject.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
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
            Document document = user.toDocument(); // Convert User to MongoDB Document
            collection.insertOne(document); // Insert the document into the collection
            user.setId(document.getObjectId("_id").toHexString()); // Set the generated ID back to the User object
            logger.info("User inserted successfully: {}", user);
        } catch (Exception e) {
            logger.error("Error inserting user: {}", e.getMessage(), e);
        }
    }

    public User getUserById(String someUserId) {
        try {
            logger.info("Retrieving user by ID: {}", someUserId);
            ObjectId objectId = new ObjectId(someUserId); // Convert String to ObjectId
            Document doc = collection.find(new Document("_id", objectId)).first();
            if (doc != null) {
                return User.fromDocument(doc);
            } else {
                logger.warn("No user found with ID: {}", someUserId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error retrieving user by ID: {}", e.getMessage(), e);
            return null;
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