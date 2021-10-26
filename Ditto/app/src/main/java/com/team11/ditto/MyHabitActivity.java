package com.team11.ditto;
/*
The Fragment Class for MyHabit Activity Screen
 */

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class MyHabitActivity extends Fragment implements AddHabitFragment.OnFragmentInteractionListener {


    //Declare variables for the list of habits
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String string);
    }


    public static void getInfo() {
        //retrieve values from the add fragment
        String title = (String) MainActivity.habitBundle.get("help");
        String reason = (String) MainActivity.habitBundle.get("help1");
        String date = (String) MainActivity.habitBundle.get("help2");

        Habit nHabit =new Habit(title, reason, date);
        habitAdapter.add(nHabit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.fragment_my_habit_activity, container, false);
        habitListView = (ListView) view.findViewById(R.id.list);
        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(getContext(), habitDataList);
        habitListView.setAdapter(habitAdapter);

        //add habit button action
        final FloatingActionButton addCityButton = view.findViewById(R.id.add_habit);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getChildFragmentManager(), "ADD_HABIT");




            }
        });



        /*
    @Override
    public void onOkPressed(Habit newHabit) {
        habitAdapter.add(newHabit);

    }

         */


        return view;
    }



    @Override
    public void onOkPressed(Habit newHabit) {

    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhabits);

        habitListView = findViewById(R.id.my_habit_list);
        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(this, habitDataList);
        habitListView.setAdapter(habitAdapter);


        //add habit button action
        final FloatingActionButton addCityButton = findViewById(R.id.add_habit);
        addCityButton.setOnClickListener(new View.OnClickListener() {
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


     */
}