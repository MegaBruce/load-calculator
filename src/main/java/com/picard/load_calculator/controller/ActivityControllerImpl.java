package com.picard.load_calculator.controller;

import com.mongodb.client.model.Filters;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Period;
import com.picard.load_calculator.repository.ActivityRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class ActivityControllerImpl implements ActivityController {
    private ActivityRepository activityRepository;

    public ActivityControllerImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ObjectId save(Activity activity) {
        activity.setLoad(activity.getRpe() * activity.getDuration());
        return this.activityRepository.save(activity);
    }

    @Override
    public List<Activity> findActivitiesByPeriod(Period period) {
        Document query = new Document().append()
        return null;
    }

    @Override
    public List<Activity> getAllActivities() {
        return this.activityRepository.getAll();
    }
}
