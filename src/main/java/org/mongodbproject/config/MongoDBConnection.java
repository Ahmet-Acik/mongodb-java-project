package org.mongodbproject.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.Properties;

public class MongoDBConnection {
    private static final String CONNECTION_STRING;
    private static final String DATABASE_NAME;

    static {
        Properties properties = new Properties();
        try {
            var inputStream = MongoDBConnection.class.getClassLoader().getResourceAsStream("application.properties");
            if (inputStream == null) {
                throw new RuntimeException("application.properties file not found in the classpath");
            }
            properties.load(inputStream);

            String host = properties.getProperty("spring.data.mongodb.host", "localhost");
            String port = properties.getProperty("spring.data.mongodb.port", "27017");
            CONNECTION_STRING = "mongodb://" + host + ":" + port;
            DATABASE_NAME = properties.getProperty("spring.data.mongodb.database", "practiceDB");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load MongoDB configuration from application.properties", e);
        }
    }

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}