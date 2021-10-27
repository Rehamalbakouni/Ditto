package com.team11.ditto;
/*
The Fragment Class for MyHabit Activity Screen
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class MyHabitActivity extends FragmentActivity implements AddHabitFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;

    public static Bundle habitBundle = new Bundle();
    //Declare variables for the list of habits
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String string);
    }


    public static void getInfo() {
        //retrieve values from the add fragment
        String title = (String) habitBundle.get("help");
        String reason = (String) habitBundle.get("help1");
        String date = (String) habitBundle.get("help2");

        Habit nHabit = new Habit(title, reason, date);
        habitAdapter.add(nHabit);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.fragment_my_habit_activity, container, false);
        habitListView = (ListView) view.findViewById(R.id.list);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);

        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        Habit habit1 = new Habit("test", "reason", "hi");
        Habit habit2 = new Habit("second habit in the list", "this is the reason given for the habit", "02-02-02");

        habitAdapter.add(habit1);
        habitAdapter.add(habit2);
        onBackPressed();
        //add habit button action
        final FloatingActionButton addHabitButton = view.findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
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
                    Intent intent = new Intent(MyHabitActivity.this,MyHabitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 2 is for Due Today
                else if (tab.getPosition() == 2){
                    Intent intent = new Intent(MyHabitActivity.this,DueToday.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 3 is for Profile
                else if (tab.getPosition() == 3){
                    // DO NOTHING
                    ;
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
        TabLayout.Tab tab = (tabLayout).getTabAt(3);

        if (tab != null) {
            tab.select();
        }

    }

}