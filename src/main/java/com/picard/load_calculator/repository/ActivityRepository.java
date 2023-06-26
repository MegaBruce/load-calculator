package com.picard.load_calculator.repository;

import com.picard.load_calculator.model.Activity;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public interface ActivityRepository {
    ObjectId save(Activity activity);
    boolean update(Activity activity);
    void delete(ObjectId id);
    List<Activity> getAll();
    List<Activity> search(Document query);

    List<Integer> getActivitiesLoadFromPast4weeks(Date today);
    List<Integer> getActivitiesLoadFromThisWeek(Date today);

}
