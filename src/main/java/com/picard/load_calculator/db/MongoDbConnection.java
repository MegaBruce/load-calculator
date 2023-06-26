package com.picard.load_calculator.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class MongoDbConnection {
    private final String dbName = "myCoachingApp";
    private final String connectionString = "mongodb+srv://app-de-test:app-de-test@cluster0.jg9rphv.mongodb.net/?retryWrites=true&w=majority";

    @Getter
    private MongoClient mongoClient;
    public void connect(){
        this.mongoClient = MongoClients.create(connectionString);
    }

    public MongoCollection<Document> getCollection (String collection) {
        MongoDatabase database = this.mongoClient.getDatabase(dbName);
        return database.getCollection(collection);
    }

    public void closeConnection(){
        this.mongoClient.close();
    }


}
