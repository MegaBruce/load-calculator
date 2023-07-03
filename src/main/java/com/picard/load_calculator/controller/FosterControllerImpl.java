package com.picard.load_calculator.controller;

import com.picard.load_calculator.helper.CalculateSD2;
import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Foster;
import com.picard.load_calculator.model.Period;
import com.picard.load_calculator.model.TrainningState;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
public class FosterControllerImpl implements FosterController {
    private ActivityController activityController;

    public FosterControllerImpl (ActivityController activityController) {
        this.activityController = activityController;
    }
    @Override
    public Foster getTrainningState(LocalDate date) {
        Foster foster = new Foster(2778, 1.8, 5000, -2222, 0.95);
        List<Activity> activitiesForThisWeek;
        List<Activity> activitiesForPast4Weeks;
        Period weekPeriod = DateHelper.getWeekPeriod(date);
        Period fourWeekPeriod = DateHelper.get4WeeksPeriod(date);
        
        activitiesForThisWeek = this.activityController
                .findActivitiesByPeriod(weekPeriod);
        activitiesForPast4Weeks = this.activityController
                .findActivitiesByPeriod(fourWeekPeriod);

        double totalWeekLoad = activitiesForThisWeek
                .stream()
                .map(a -> a.getLoad())
                .reduce(0, (a, b) -> a + b);

        double monotony =
                (totalWeekLoad/7) / CalculateSD2.doStandardDerivation(
                        getLoadArrayForAPeriod(activitiesForThisWeek, weekPeriod)
                );

        double totalPast4WeeksLoad = activitiesForPast4Weeks
                .stream()
                .map(a -> a.getLoad())
                .reduce(0, (a, b) -> a + b);

        foster.setLoad((int) totalWeekLoad);
        foster.setAcwr(totalWeekLoad/(totalPast4WeeksLoad/4));
        foster.setMonotony(monotony);
        foster.setStrain((int) (totalWeekLoad*monotony));
        foster.setFitness(foster.getLoad() - foster.getStrain());

        return foster;
    }

    private static List<Integer> getLoadArrayForAPeriod(List<Activity> activitiesForThisWeek, Period weekPeriod) {
        List<Integer> loadArray = new ArrayList<>();

        for (
                LocalDate date = weekPeriod.getStartDate();
                date.isBefore(weekPeriod.getEndDate().plusDays(1));
                date = date.plusDays(1)
        ) {
            LocalDate finalDate = date;
            int dayLoad = activitiesForThisWeek.stream()
                    .filter(activity -> DateHelper.areDatesEqual(activity.getDate(), finalDate))
                    .map(activity -> activity.getLoad())
                    .reduce(0, (a,b) -> a+b);
            loadArray.add(dayLoad | 0);
        }
        return loadArray;
    }

    public TrainningState calculateTrainningState(Foster foster){
        if (
            foster.getMonotony() > 2.5 ||
            foster.getStrain() > 10000 ||
            foster.getAcwr() > 1.5
        ){
            return TrainningState.INJURY;
        }
        if (
                (foster.getMonotony() > 2 &&
                        foster.getMonotony() < 2.5 )
                        ||
                        (foster.getStrain() > 6000 &&
                                foster.getStrain() < 10000)
        ) {
            return TrainningState.TIRED;
        }
        if (
                foster.getMonotony() < 2 &&
                        foster.getStrain() < 6000 &&
                        foster.getAcwr() > 0.8 &&
                        foster.getAcwr() < 1.3
        ) {
            return TrainningState.OPTIMAL;
        }
        return TrainningState.RAS;
    }
}
