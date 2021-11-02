package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements SwitchTabs {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    FirebaseFirestore db; //when they addcity button we need to dump into db
    //public static Bundle habitBundle = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If device has userID, go to app - else, go to login
        if (!hasUserID()) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(intent);
        }

        tabLayout = findViewById(R.id.tabs);

        currentTab(tabLayout, HOME_TAB);
        switchTabs(this, tabLayout, HOME_TAB);

    }

    // Check if device has stored userID
    private Boolean hasUserID() {

        LocalStorage ls = new LocalStorage(this);
        String uid = ls.getUserID();

        if (uid.equals(this.getString(R.string.null_user_id))) {
            return false;
        }
        return true;
    }

}