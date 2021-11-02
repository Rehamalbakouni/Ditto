package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActiveUser extends User{

    static ActiveUser activeUser;

    private ActiveUser(User user){
        activeUser = (ActiveUser) user;
    }

    public @Nullable ActiveUser getActiveUser(){
        if (activeUser == null) {
            return null;
        }
        else{
            return activeUser;
        }
    }

    public void setActiveUser(@NonNull User user) {
        if (getActiveUser() == null) {
            activeUser = new ActiveUser(user);
        }
        else{
            throw new RuntimeException("Active User Error");
        }
    }

    public void logout(){
        activeUser = null;
    }
}
