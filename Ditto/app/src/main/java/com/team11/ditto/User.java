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
    public User(){
        //get latest user id # from the database
        //this.id = ...

        //set initial username to the id. The user can change it later
        this.username = this.id;

    }

    //Username getter
    public String getUsername() {
        return username;
    }

    //Username setter
    public void setUsername(String username) {
        this.username = username;
    }

}
