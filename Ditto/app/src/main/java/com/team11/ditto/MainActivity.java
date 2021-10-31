package com.team11.ditto;
/*
Class for Habit Event Activity
Goals: To create an interface for these action because there is repetition between MyHabitActivity and the Homepage
 */
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SwitchTabs, AddHabitEventFragment.OnFragmentInteractionListener {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    ListView habitEventListView;
    private ArrayList<HabitEvent> habitEventsData;
    private ArrayAdapter<HabitEvent> habitEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);

        habitEventListView = findViewById(R.id.list_habitevent);

        habitEventsData = new ArrayList<>();
        habitEventAdapter = new CustomListHabitEvent(MainActivity.this, habitEventsData);

        habitEventListView.setAdapter(habitEventAdapter);

        habitEventAdapter.add(new HabitEvent("hahahaha", "this is acomment", "", ""));

        currentTab(tabLayout, HOME_TAB);
        switchTabs(this, tabLayout, HOME_TAB);

        final FloatingActionButton addHabitEventButton = findViewById(R.id.add_habit_event);
        addHabitEventButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                new AddHabitEventFragment().show(getSupportFragmentManager(), "ADD_HABIT_EVENT");
            }
        });
    }

    @Override
    public void onOkPressed(HabitEvent newHabitEvent) {
        habitEventAdapter.add(newHabitEvent);
    }
}