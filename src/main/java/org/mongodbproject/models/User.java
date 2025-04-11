package org.mongodbproject.models;

import org.bson.Document;

public class User {
    private String id;
    private String name;
    private String email;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Document toDocument() {
        return new Document("name", name).append("email", email);
    }

    public static User fromDocument(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").toString());
        user.setName(document.getString("name"));
        user.setEmail(document.getString("email"));
        return user;
    }
}