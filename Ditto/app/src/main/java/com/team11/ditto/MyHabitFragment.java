package com.team11.ditto;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.team11.ditto.placeholder.PlaceholderContent;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class MyHabitFragment extends FragmentActivity {
    //Declare variables for the list of habits
    ListView habitListView;
    ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_habit_list);

        habitListView = findViewById(R.id.list);
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



    public void onOkPressed(Habit newHabit) {
        habitAdapter.add(newHabit);

    }





}