package com.picard.load_calculator;

import com.mongodb.client.MongoCollection;
import com.picard.load_calculator.controller.ActivityController;
import com.picard.load_calculator.controller.ActivityControllerImpl;
import com.picard.load_calculator.controller.FosterController;
import com.picard.load_calculator.controller.FosterControllerImpl;
import com.picard.load_calculator.db.MongoDbConnection;
import com.picard.load_calculator.gui.Home;
import com.picard.load_calculator.repository.ActivityRepository;
import com.picard.load_calculator.repository.ActivityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        MongoDbConnection dbConnection = new MongoDbConnection();
        dbConnection.connect();
        MongoCollection activitiesCollection = dbConnection.getCollection("activities");
        ActivityRepository activityRepository = new ActivityRepositoryImpl(activitiesCollection);
        ActivityController activityController = new ActivityControllerImpl(activityRepository);
        FosterController fosterController = new FosterControllerImpl(activityController);

        new Home(activityController,fosterController);
    }
}
