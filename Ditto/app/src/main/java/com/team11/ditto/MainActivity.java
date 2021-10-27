package com.team11.ditto;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    FirebaseFirestore db; //when they addcity button we need to dump into db
    //public static Bundle habitBundle = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);

        currentTab();
        switchTabs();

    }




    public void switchTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // position 0 is for home
                if(tab.getPosition() ==0){

                    Log.d(TAG, "onTabSelected: tab 0 is selected");
                    ;
                }

                // position 1 is for MyHabits
                else if (tab.getPosition() == 1){
                    Intent intent = new Intent(MainActivity.this, MyHabitActivity.class);

                    startActivity(intent);
                }

                // position 2 is for Due Today
                else if (tab.getPosition() == 2){
                    Intent intent = new Intent(MainActivity.this,DueToday.class);

                    startActivity(intent);
                }

                // position 3 is for Profile
                else if (tab.getPosition() == 3){
                    Intent intent = new Intent(MainActivity.this,UserProfileActivity.class);

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
        TabLayout.Tab tab = (tabLayout).getTabAt(0);
        if (tab != null) {
            tab.select();
        }

    }
}