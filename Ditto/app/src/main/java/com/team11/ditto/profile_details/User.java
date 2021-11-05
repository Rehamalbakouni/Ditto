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


    /**
     * Constructor for a User object
     * @param username user chosen self-identifying string
     * @param password user chosen authentication string
     * @param age user's age
     *
     * Other attributes initialized to defaults can be added to or changed later
     */
    public User(String username, String password, int age){
        this.username = username;
        this.habits = new ArrayList<>();
        this.iFollow = new ArrayList<>();
        this.followMe = new ArrayList<>();
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

    /**
     * Empty constructor to allow for ActiveUser flexibility
     */
    protected User(){
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for age
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Getter for habit list
     * @return habit list
     */
    public ArrayList<Habit> getHabits() {
        return habits;
    }

    /**
     * Getter for followed list
     * @return followed list
     */
    public ArrayList<User> getIFollow() {
        return iFollow;
    }

    /**
     * Getter for following list
     * @return following list
     */
    public ArrayList<User> getFollowMe() {
        return followMe;
    }

    /**
     * Add a User object to this User's list of Users they follow
     * @param toFollow User to follow
     */
    public void addFollowing(User toFollow){
        this.iFollow.add(toFollow);
    }

    /**
     * Getter for photo
     * @return photo
     */
    public Drawable getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * Setter for photo
     * @param profilePhoto new photo
     */
    public void setProfilePhoto(Drawable profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    /**
     * Setter for id
     * @param id new id
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * Getter for id
     * @return id existing id
     */
    public String getID(String id){
        return this.id;
    }
}
