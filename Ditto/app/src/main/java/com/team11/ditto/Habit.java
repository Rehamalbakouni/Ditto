package com.team11.ditto;

/*
Purpose: Habit class represents a habit object and holds data for
title
reason
date
Design Rationale: set getters and setters for the data that Habit holds
 */

import java.util.ArrayList;

public class Habit {
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
}
