package com.picard.load_calculator.controller;

import com.picard.load_calculator.helper.CalculateSD2;
import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Foster;
import com.picard.load_calculator.model.Period;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class FosterControllerImpl implements FosterController {
    private ActivityController activityController;

    public FosterControllerImpl (ActivityController activityController) {
        this.activityController = activityController;
    }
    @Override
    public Foster getTrainningState(Date date) {
        Foster foster = new Foster();
        List<Activity> activitiesForThisWeek;
        List<Activity> activitiesForPast4Weeks;
        Period weekPeriod = DateHelper.getWeekPeriod(date);
        
        activitiesForThisWeek = this.activityController
                .findActivitiesByPeriod(weekPeriod);
        activitiesForPast4Weeks = this.activityController
                .findActivitiesByPeriod(DateHelper.get4WeeksPeriod(date));

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
        Date dateIterator = (Date) weekPeriod.getStartDate().clone();
        Date dateLimit = (Date) weekPeriod.getEndDate().clone();
        dateLimit.setDate(dateLimit.getDate() + 1);
        int i = 0;
        do {
            int dayLoad = activitiesForThisWeek.stream()
                    .filter(activity -> DateHelper.areDatesEqual(activity.getDate(), dateIterator))
                    .map(activity -> activity.getLoad())
                    .reduce(0, (a,b) -> a+b);
            loadArray.add(dayLoad | 0);
            dateIterator.setDate(dateIterator.getDate() + 1);
        }
        while (!DateHelper.areDatesEqual(dateIterator, dateLimit));

        return loadArray;
    }
}
