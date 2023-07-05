package com.picard.load_calculator.mapper;

import com.picard.load_calculator.model.Activity;
import org.bson.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ActivityMapper {
    public static Document activityToDocument(Activity activity) {
        Document document = new Document()
                .append("name", activity.getName())
                .append("duration", activity.getDuration())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad())
                .append("date", activity.getDate())
                ;
        return document;
    }
    public static Activity documentToActivity(Document document) {
        Activity activity = new Activity(
                document.get("name").toString(),
                Integer.parseInt(document.get("duration").toString()),
                Integer.parseInt(document.get("rpe").toString()),
                Integer.parseInt(document.get("load").toString()),
                document.getDate("date").toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                document.getObjectId("_id")
        );
        return activity;
    }
}
