package org.mongodbproject.models;

import org.bson.Document;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private Address address; // Nested object
    private List<String> hobbies; // Array of strings

    // Getters and setters

    public Document toDocument() {
        Document addressDoc = new Document("street", address.getStreet())
                .append("city", address.getCity())
                .append("zipCode", address.getZipCode());

        return new Document("name", name)
                .append("email", email)
                .append("address", addressDoc)
                .append("hobbies", hobbies);
    }

    public static User fromDocument(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").toString());
        user.setName(document.getString("name"));
        user.setEmail(document.getString("email"));

        Document addressDoc = document.get("address", Document.class);
        if (addressDoc != null) {
            Address address = new Address();
            address.setStreet(addressDoc.getString("street"));
            address.setCity(addressDoc.getString("city"));
            address.setZipCode(addressDoc.getString("zipCode"));
            user.setAddress(address);
        }

        user.setHobbies(document.getList("hobbies", String.class));
        return user;
    }
}