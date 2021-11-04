package com.team11.ditto;

/*
Purpose: Habit class represents a habit object and holds data for
title
reason
date
Design Rationale: set getters and setters for the data that Habit holds
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Purpose: Habit class represents a habit object and holds data for
 * title
 * reason
 * date
 * Design Rationale: set getters and setters for the data that Habit holds
 */
public class Habit implements Serializable {
    private String habitID;
    private String title;
    private String reason;
    private ArrayList<Integer> dates;

    public Habit(String title, String reason, ArrayList<Integer> dates) {
        this.title = title;
        this.reason = reason;
        this.dates = dates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<Integer> getDate() {
        return dates;
    }

    public void setDate(ArrayList<Integer> dates) {
        this.dates = dates;
    }

    public String getHabitID() {
        return habitID;
    }

    public void setHabitID(String habitID) {
        this.habitID = habitID;
    }
}
