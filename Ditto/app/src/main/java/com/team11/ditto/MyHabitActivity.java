package com.team11.ditto;
/*
The Class for MyHabit Activity Screen
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MyHabitActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener, SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;
    final String TAG = "addd";
    HashMap<String, String> data = new HashMap<>();



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

        currentTab(tabLayout, MY_HABITS_TAB);
        switchTabs(this, tabLayout, MY_HABITS_TAB);


        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection

        //add habit button action
        final FloatingActionButton addHabitButton = findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");

            }
        });
    }

    @Override
    public void onOkPressed(Habit newHabit) {
        //when the user clicks the add button, we want to add to the db and display the new entry

        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final String date = newHabit.getDate();
        final CollectionReference collectionReference = db.collection("Habit");


        if (title.length()>0 && reason.length()>0) {
            //if there is some data in edittext field, then create new key-value pair
            data.put("title", title);
            data.put("reason", reason);
            data.put("date_to_start", date);

            //get unique document id. our format: habit title + current date, time added
            Date currentTime = Calendar.getInstance().getTime();

            collectionReference
                    .document(title)
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //method which gets executed when the task is successful
                            habitAdapter.add(newHabit);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //method that gets executed if there's a problem
                            Log.d(TAG, "Data could not be added!" + e.toString());

                        }
                    });
        }


    }


    public void onBackPressed() {

        Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}