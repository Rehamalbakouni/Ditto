package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class DueToday extends AppCompatActivity {



    FirebaseFirestore db; //when they addcity button we need to dump into db
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        setContentView(R.layout.due_today);
        // Go back to main activity ob back button press
        Date today = Calendar.getInstance().getTime();
        setTitle(today.toString());
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        currentTab();
        switchTabs();


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void switchTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // position 0 is for home
                if(tab.getPosition() ==0){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 1 is for MyHabits
                else if (tab.getPosition() == 1){
                    Intent intent = new Intent(getApplicationContext(),MyHabitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 2 is for Due Today
                else if (tab.getPosition() == 2){
                    // DO NOTHING
                    ;
                }

                // position 3 is for Profile
                else if (tab.getPosition() == 3){
                    Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void currentTab(){
        TabLayout.Tab tab = (tabLayout).getTabAt(2);
        if (tab != null) {
            tab.select();
        }

    }


}

