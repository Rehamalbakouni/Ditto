package com.team11.ditto;
/*
The Fragment Class for MyHabit Activity Screen
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyHabitActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String string);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);

        habitListView = findViewById(R.id.list);
        tabLayout = findViewById(R.id.tabs);


        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        Habit habit1 = new Habit("test", "reason", "hi");
        Habit habit2 = new Habit("second habit in the list", "this is the reason given for the habit", "02-02-02");

        habitAdapter.add(habit1);
        habitAdapter.add(habit2);

        currentTab();
        switchTabs();


        //add habit button action

        final FloatingActionButton addHabitButton = view.findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");
            }
        });
    }


    @Override
    public void onOkPressed(Habit newHabit) {
        habitAdapter.add(newHabit);
    }


    public void onBackPressed() {

        Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void switchTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // position 0 is for home
                if(tab.getPosition() ==0){
                    Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 1 is for MyHabits
                else if (tab.getPosition() == 1){
                    // DO NOTHING
                }

                // position 2 is for Due Today
                else if (tab.getPosition() == 2){
                    Intent intent = new Intent(MyHabitActivity.this,DueToday.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 3 is for Profile
                else if (tab.getPosition() == 3){
                    Intent intent = new Intent(MyHabitActivity.this,UserProfileActivity.class);
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
        TabLayout.Tab tab = (tabLayout).getTabAt(1);

        if (tab != null) {
            tab.select();
        }

    }

}