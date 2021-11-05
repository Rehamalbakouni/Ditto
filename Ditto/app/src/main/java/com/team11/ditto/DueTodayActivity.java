package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class DueTodayActivity extends AppCompatActivity implements SwitchTabs {
    FirebaseFirestore db;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        setContentView(R.layout.activity_due_today);
        // Go back to main activity ob back button press
        Date today = Calendar.getInstance().getTime();
        setTitle(today.toString());
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        currentTab(tabLayout, DUE_TODAY_TAB);
        switchTabs(this, tabLayout, DUE_TODAY_TAB);


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

