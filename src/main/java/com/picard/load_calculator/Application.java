package com.picard.load_calculator;

import com.picard.load_calculator.controller.ActivityControllerImpl;
import com.picard.load_calculator.db.MongoDbConnection;
import com.picard.load_calculator.gui.Home;
import com.picard.load_calculator.repository.ActivityRepository;
import com.picard.load_calculator.repository.ActivityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class Application {

    public static void main(String[] args) {
        MongoDbConnection dbConnection = new MongoDbConnection();
        dbConnection.connect();
        ActivityRepository activityRepository = new ActivityRepositoryImpl(dbConnection.getCollection("activities"));
        new Home(new ActivityControllerImpl(activityRepository));
    }
}
