package com.team11.ditto;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyHabitsActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener {

    //Declare variables for the list of habits
    ListView habitListView;
    ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;


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
}
