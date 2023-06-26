package com.picard.load_calculator.helper;

import com.picard.load_calculator.model.Period;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

public class DateHelper {

    public static Period getWeekPeriod(Date date){
        int dayNumber = date.getDay();

        Date startDate = (Date) date.clone();
        Date endDate = (Date) date.clone();

        if (dayNumber == 0) {
            startDate.setDate(date.getDate() - 6);
        } else {
            startDate.setDate(date.getDate() - (dayNumber - 1));
            endDate.setDate(date.getDate() + (7 - dayNumber));
        }
        return new Period(startDate, endDate);
    }

    public static Period get4WeeksPeriod (Date date){
        int dayNumber = date.getDay();

        Date startDate = (Date) date.clone();
        Date endDate = (Date) date.clone();

        if (dayNumber == 0) {
            startDate.setDate(date.getDate() - 27);
        } else {
            startDate.setDate(date.getDate() - ((dayNumber - 1) + 21));
            endDate.setDate(date.getDate() + (7 - dayNumber));
        }
        return new Period(startDate, endDate);
    }

    public static boolean areDatesEqual(Date date1, Date date2) {
        return (
                (date1.getYear() == date2.getYear())
                && (date1.getMonth() == date2.getMonth())
                && (date1.getDay() == date2.getDay())
        );
    }
}
