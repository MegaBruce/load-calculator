package com.picard.load_calculator.helper;

import com.picard.load_calculator.model.Period;

import java.time.LocalDate;
import java.util.Date;

public class DateHelper {

    public static Period getWeekPeriod(LocalDate date){
        int dayNumber = date.getDayOfWeek().getValue();

        LocalDate startDate;
        LocalDate endDate;

        if (dayNumber == 0) {
            startDate = date.minusDays(6);
            endDate = date;
        } else {
            startDate = date.minusDays(dayNumber-1);
            endDate = date.plusDays(7-dayNumber);
        }
        return new Period(startDate, endDate);
    }

    public static Period get4WeeksPeriod (LocalDate date){
        int dayNumber = date.getDayOfWeek().getValue();

        LocalDate startDate;
        LocalDate endDate;

        if (dayNumber == 0) {
            startDate = date.minusDays(27);
            endDate = date;
        } else {
            startDate = date.minusDays((dayNumber - 1) + 21);
            endDate = date.plusDays(7-dayNumber);
        }
        return new Period(startDate, endDate);
    }

    public static boolean areDatesEqual(LocalDate date1, LocalDate date2) {
        return (
                (date1.getYear() == date2.getYear())
                && (date1.getDayOfYear() == date2.getDayOfYear())
        );
    }
}
