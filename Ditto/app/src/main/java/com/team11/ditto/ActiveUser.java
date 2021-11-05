package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Represents the User who is currently logged in
 * @author Courtenay Laing-Kobe
 */
public class ActiveUser extends User{

    static ActiveUser activeUser;

    /**
     * Constructor for the ActiveUser
     * @param user : takes an argument of the user object related to the person logged in
     */
    private ActiveUser(User user){
        activeUser = (ActiveUser) user;
    }

    /**
     * Gets the current ActiveUser
     * @return the current ActiveUser if there is one, otherwise null
     */
    public @Nullable ActiveUser getActiveUser(){
        if (activeUser == null) {
            return null;
        }
        else{
            return activeUser;
        }
    }

    /**
     * Changes the user object associated with the ActiveUser
     * @param user : change the ActiveUser to the new object 'user'
     * @throws RuntimeException when previous User is not logged out
     */
    public void setActiveUser(@NonNull User user) {
        if (getActiveUser() == null) {
            activeUser = new ActiveUser(user);
        }
        else{
            throw new RuntimeException("Active User Error");
        }
    }

    /**
     * Sets the ActiveUser to null, effectively logging out the user
     */
    public void logout(){
        activeUser = null;
    }
}
