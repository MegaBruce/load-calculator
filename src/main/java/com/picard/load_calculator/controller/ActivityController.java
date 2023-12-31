package com.picard.load_calculator.controller;

import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Period;
import org.bson.types.ObjectId;

import java.util.List;

public interface ActivityController {

    ObjectId save(Activity activity);
    List<Activity> findActivitiesByPeriod(Period period);
    List<Activity> getAllActivities();
}
