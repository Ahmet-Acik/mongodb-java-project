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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Document toDocument() {
        Document document = new Document()
                .append("name", name)
                .append("email", email);

        if (address != null) {
            document.append("address", new Document()
                    .append("street", address.getStreet())
                    .append("city", address.getCity())
                    .append("zipCode", address.getZipCode()));
        }
        if (this.hobbies != null) {
            document.append("hobbies", this.hobbies);
        }
        return document;
    }

    public static User fromDocument(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").toHexString());
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
        List<String> hobbies = document.getList("hobbies", String.class);
        if (hobbies != null) {
            user.setHobbies(hobbies);
        }
        return user;
    }
}