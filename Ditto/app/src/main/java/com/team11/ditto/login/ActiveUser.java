package com.team11.ditto.login;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team11.ditto.WelcomeActivity;
import com.team11.ditto.profile_details.User;

/**
 * Represents the User who is currently logged in
 * @author Courtenay Laing-Kobe
 */
public class ActiveUser extends User {

    static ActiveUser activeUser;
    FirebaseUser fbUser;

    /**
     * Constructor for the ActiveUser
     * @param user : takes an argument of the user object related to the person logged in
     */
    private ActiveUser(User user){
        activeUser = (ActiveUser) user;
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public ActiveUser() {
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
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
     * Returns the UID of the signed in user on Firebase
     * @return String : String form of the current user UID
     */
    public String getUID() {
        if (fbUser != null) {
            return fbUser.getUid();
        } else {
            return "";
        }
    }

    /**
     * Sets the ActiveUser to null
     * Logs the user out of Firebase
     */
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        activeUser = null;
    }
}
