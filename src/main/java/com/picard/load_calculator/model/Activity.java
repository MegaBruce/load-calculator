package com.picard.load_calculator.model;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Activity {

    private ObjectId id;
    private String name;
    private int duration;
    private int rpe;
    private int load;
    private LocalDate date;



    public Activity(String name, int duration, int rpe, int load, LocalDate date, ObjectId id) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.rpe = rpe;
        this.load = load;
        this.date = date;
    }

    public Activity(String name, int duration, int rpe, LocalDate date) {
        this.name = name;
        this.duration = duration;
        this.rpe = rpe;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRpe() {
        return rpe;
    }

    public void setRpe(int rpe) {
        this.rpe = rpe;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
