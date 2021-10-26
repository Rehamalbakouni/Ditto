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

import java.util.AbstractCollection;
import java.util.ArrayList;

public class MyHabitActivity extends Fragment implements AddHabitFragment.OnFragmentInteractionListener {

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.fragment_my_habit_activity, container, false);
        habitListView = (ListView) view.findViewById(R.id.list);
        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(getContext(), habitDataList);
        habitListView.setAdapter(habitAdapter);

        Habit habit1 = new Habit("test", "reason", "hi");
        Habit habit2 = new Habit("second habit in the list", "this is the reason given for the habit", "02-02-02");

        habitAdapter.add(habit1);
        habitAdapter.add(habit2);

        //add habit button action
        final FloatingActionButton addCityButton = view.findViewById(R.id.add_habit);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getChildFragmentManager(), "ADD_HABIT");
            }
        });

        return view;
    }

    @Override
    public void onOkPressed(Habit newHabit) {
        habitAdapter.add(newHabit);
    }
}