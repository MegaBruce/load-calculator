package com.picard.load_calculator.controller;

import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Period;
import com.picard.load_calculator.repository.ActivityRepository;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.*;

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
        Bson query = and(
                gte("date", period.getStartDate()),
                lt("date", period.getEndDate())
        );
        return this.activityRepository.search(query);
    }

    @Override
    public List<Activity> getAllActivities() {
        return this.activityRepository.getAll();
    }
}
