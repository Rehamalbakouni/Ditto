package com.team11.ditto.profile_details;

import android.graphics.drawable.Drawable;

import com.team11.ditto.habit.Habit;

import java.util.ArrayList;

public class User extends Object{

    //User attributes
    private String id;
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<User> iFollow;
    private ArrayList<User> followMe;
    private Drawable profilePhoto;
    private String password;
    private int age;


    //Constructor
    public User(String username, String password, int age){
        this.username = username;
        this.habits = new ArrayList<Habit>();
        this.iFollow = new ArrayList<User>();
        this.followMe = new ArrayList<User>();
        this.profilePhoto = Drawable.createFromPath("ic_action_profile.png");
        this.password = password;
        this.age = age;
    }

    public User(String username, String password){
        this.username = username;
        this.habits = new ArrayList<Habit>();
        this.iFollow = new ArrayList<User>();
        this.followMe = new ArrayList<User>();
        this.profilePhoto = Drawable.createFromPath("ic_action_profile.png");
        this.password = password;
    }

    public User(){

    }



    //Username getter
    public String getUsername() {
        return username;
    }

    //Username setter
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<Habit> getHabits() {
        return habits;
    }

    public ArrayList<User> getIFollow() {
        return iFollow;
    }

    public ArrayList<User> getFollowMe() {
        return followMe;
    }

    public void addFollowing(User toFollow){
        this.iFollow.add(toFollow);
    }

    public Drawable getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Drawable profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setID(String id){
        this.id = id;
    }
}
