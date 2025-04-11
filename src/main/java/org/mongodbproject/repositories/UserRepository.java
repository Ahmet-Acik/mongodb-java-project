package org.mongodbproject.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.mongodbproject.config.MongoDBConnection;
import org.mongodbproject.models.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final MongoCollection<Document> collection;

    public UserRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("users");
    }

    public void insertUser(User user) {
        collection.insertOne(user.toDocument());
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : collection.find()) {
            users.add(User.fromDocument(doc));
        }
        return users;
    }
}