package com.picard.load_calculator.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.picard.load_calculator.Application;
import com.picard.load_calculator.model.Activity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.picard.load_calculator.mapper.ActivityMapper.activityToDocument;
import static com.picard.load_calculator.mapper.ActivityMapper.documentToActivity;

public class ActivityRepositoryImpl implements ActivityRepository {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    MongoCollection<Document> collection;
    public ActivityRepositoryImpl(MongoCollection<Document> collection){
        this.collection = collection;
    }

    @Override
    public List<Activity> getAll() {
        List<Activity> activities = new ArrayList<>();
        for (Document document : this.collection.find()) {
            activities.add(documentToActivity(document));
        }
        return activities;
    }

    @Override
    public List<Activity> search(Document query) {
        List<Activity> activities = new ArrayList<>();
        for (Document document : this.collection.find(query)) {
            activities.add(documentToActivity(document));
        }
        return activities;
    }

    @Override
    public List<Integer> getActivitiesLoadFromPast4weeks(Date today) {
        return null;
    }

    @Override
    public List<Integer> getActivitiesLoadFromThisWeek(Date today) {
        return null;
    }

    @Override
    public ObjectId save(Activity activity) {
        InsertOneResult result = this.collection.insertOne(activityToDocument(activity));
        return result
                .getInsertedId()
                .asObjectId()
                .getValue()
                ;
    }

    @Override
    public boolean update(Activity activity) {
        Bson query = Filters.eq("_id", activity.getId());
        Document update = activityToDocument(activity) ;
        UpdateResult upResult = collection.replaceOne(query, update);
        return upResult.wasAcknowledged();
    }

    @Override
    public void delete(ObjectId id) {
        Bson query = Filters.eq("_id", id);
        DeleteResult delResult = collection.deleteOne(query);
        logger.info("Deleted a document: {}", delResult.getDeletedCount());
    }
}
