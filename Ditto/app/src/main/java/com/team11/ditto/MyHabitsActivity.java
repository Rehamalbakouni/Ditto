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

public class MyHabitsActivity extends AppCompatActivity{

    //Declare variables for the list of habits
    ListView habitListView;
    ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhabits);

        habitListView = findViewById(R.id.);
        habitDataList = new ArrayList<>();

        habitAdapter = new CustomList_Habit(this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection
        final CollectionReference collectionReference = db.collection("Habit");

        //add habit button action
        final FloatingActionButton addCityButton = findViewById(R.id.add_med_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddMedFragment(dosage).show(getSupportFragmentManager(), "ADD_MED");


            }
        });

    }

}
