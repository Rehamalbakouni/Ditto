package com.team11.ditto;
/*
Role: Class for Habit Event Activity, be able to see you feed and add a habit event
Goals:
    there is repetition between MyHabitActivity and the Homepage when creating fragments and listviews
    solve by making a more object oriented design
 */

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Role: Class for Habit Event Activity, be able to see you feed and add a habit event
 * Goals:
 *     there is repetition between MyHabitActivity and the Homepage when creating fragments and listviews
 *     solve by making a more object oriented design
 * @author: Kelly Shih, Aidan Horemans, Vivek Malhotra
 */
public class MainActivity extends AppCompatActivity implements SwitchTabs, Firebase, AddHabitEventFragment.OnFragmentInteractionListener {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    ListView habitEventListView;
    private ArrayList<HabitEvent> habitEventsData;
    private ArrayAdapter<HabitEvent> habitEventAdapter;
    private FirebaseFirestore db;
    HashMap<String, Object> data = new HashMap<>();
    private ActiveUser activeUser;


    /**
     * Create the Activity instance for the "Homepage" screen, control flow of actions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);

        habitEventListView = findViewById(R.id.list_habitevent);
        habitEventsData = hEventsFirebase;
        habitEventAdapter = new CustomListHabitEvent(MainActivity.this, habitEventsData);
        habitEventListView.setAdapter(habitEventAdapter);

        currentTab(tabLayout, HOME_TAB);
        switchTabs(this, tabLayout, HOME_TAB);

        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection

        //Notifies if cloud data changes (from Firebase Interface)
        autoSnapshotListener(db, habitEventAdapter, HABIT_EVENT_KEY);

        final FloatingActionButton addHabitEventButton = findViewById(R.id.add_habit_event);
        addHabitEventButton.setOnClickListener(view -> new AddHabitEventFragment().show(getSupportFragmentManager(), "ADD_HABIT_EVENT"));

    }

    /**
     * Adds a habitevent to firestore "HabitEvent" and adds the habitevent ID to the list of habitEvents for the habit in "Habit"
     * Adds the habitevent to the listview
     * @param newHabitEvent
     */
    @Override
    public void onOkPressed(HabitEvent newHabitEvent) {
        pushHabitEventData(db, newHabitEvent);
    }
}