package org.mongodbproject.models;

import org.bson.Document;

public class Address {
    private String street;
    private String city;
    private String zipCode;

    // Getters and setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("street", street);
        document.put("city", city);
        document.put("zipCode", zipCode);
        return document;
    }

    public static Address fromDocument(Document document) {
        Address address = new Address();
        address.setStreet(document.getString("street"));
        address.setCity(document.getString("city"));
        address.setZipCode(document.getString("zipCode"));
        return address;
    }

}