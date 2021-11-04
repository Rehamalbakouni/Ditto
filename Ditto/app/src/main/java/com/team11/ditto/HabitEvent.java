package com.team11.ditto;
/*
Role: Habit Event Object that stores the data for a habit event's
habitID
comment
photograph
location
 */

/**
 * Role: Habit Event Object that stores the data for a habit event's
 * habitID
 * comment
 * photograph
 * location
 */
public class HabitEvent extends Object{
    private String habitId;
    private String comment;
    private String photo;
    private String location;

    public HabitEvent(String habitId, String comment, String photo, String location) {
        this.habitId = habitId;
        this.comment = comment;
        this.photo = photo;
        this.location = location;
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
}
