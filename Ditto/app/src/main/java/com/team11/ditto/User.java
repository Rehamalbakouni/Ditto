package com.team11.ditto;

import android.media.Image;

public class User {

    //User attributes
    private String id;
    private String username;
    private Habit[] habits;
    private User[] iFollow;
    private User[] followMe;
    private Image profilePhoto;


    //Constructor
    public User(String username){
        //get latest user id # from the database
        //this.id = ...

        //set initial username to the id. The user can change it later
        this.username = username;

    }



    //Username getter
    public String getUsername() {
        return username;
    }

    //Username setter
    public void setUsername(String username) {
        this.username = username;
    }


    public Habit[] getHabits() {
        return habits;
    }

    public void setHabits(Habit[] habits) {
        this.habits = habits;
    }

    public User[] getIFollow() {
        return iFollow;
    }

    public void setIFollow(User[] iFollow) {
        this.iFollow = iFollow;
    }

    public User[] getFollowMe() {
        return followMe;
    }

    public void setFollowMe(User[] followMe) {
        this.followMe = followMe;
    }

    public Image getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Image profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
