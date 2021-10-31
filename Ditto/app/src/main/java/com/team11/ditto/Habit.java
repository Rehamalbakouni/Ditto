package com.team11.ditto;

/*
Purpose: Habit class represents a habit object and holds data for
title
reason
date
Design Rationale: set getters and setters for the data that Habit holds
 */

public class Habit {
    private String title;
    private String reason;
    private String date;

    public Habit(String title, String reason, String date) {
        this.title = title;
        this.reason = reason;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
