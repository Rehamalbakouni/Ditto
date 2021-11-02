package com.team11.ditto;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LocalStorage(Context context) {
        this.context = context;
        // Load preference file
        this.sharedPreferences = this.context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );
        this.editor = this.sharedPreferences.edit();
    }

    // Set the userID to be locally stored
    public void setUserID(String userID) {
        editor.putString(context.getString(R.string.user_id_key), userID);
        editor.apply();
    }

    // Get the locally stored userID
    public String getUserID() {
        return sharedPreferences.getString(
                context.getString(R.string.user_id_key),
                context.getString(R.string.null_user_id));
    }



}
