package com.team11.ditto.habit_event;
/*
Role: Habit Event Object that stores the data for a habit event's
habitID
comment
photograph
location
 */

import java.io.Serializable;

/**
 * Role: Habit Event Object that stores the data for a habit event's
 * habitID
 * comment
 * photograph
 * location
 */

public class HabitEvent implements Serializable {
    private String habitId;
    private String habitTitle;
    private String comment;
    private String photo;
    private String location;

    public HabitEvent(String habitId, String comment, String photo, String location, String habitTitle) {
        this.habitId = habitId;
        this.comment = comment;
        this.photo = photo;
        this.location = location;
        this.habitTitle = habitTitle;
    }

    public String getHabitId() {
        return habitId;
    }

    public void setHabitId(String habitId) {
        this.habitId = habitId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
        this.habitTitle = habitTitle;
    }
}
