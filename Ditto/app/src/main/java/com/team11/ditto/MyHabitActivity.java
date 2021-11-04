package com.team11.ditto;
/*
Role: To display the listview of Habits for a user in the "My Habits" tab
Allow a user to add a habit, swipe left to delete a habit
Goals:
    -A user can add a habit to the database, but cannot delete a habit from the db yet
    -Add the days of week to db
    -Allow user to edit an existing habit
    -Visually make it better
 */


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**To display the listview of Habits for a user in the "My Habits" tab
 *Allow a user to add a habit, swipe left to delete a habit
 * Goals:
 *     -A user can add a habit to the database, but cannot delete a habit from the db yet
 *     -Add the days of week to db
 *     -Allow user to edit an existing habit
 *     -Visually make it better
 * @author Kelly Shih, Aidan Horemans

 */
public class MyHabitActivity extends AppCompatActivity implements
        AddHabitFragment.OnFragmentInteractionListener, SwitchTabs, Firebase {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    //SwipeMenuListView habitListView;
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;

    /**
     * Create the Activity instance for the "My Habits" screen, control flow of actions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);

        db = FirebaseFirestore.getInstance();

        habitDataList = habitsFirebase;
        habitListView = findViewById(R.id.list);
        tabLayout = findViewById(R.id.tabs);

        habitAdapter = new CustomListHabit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        currentTab(tabLayout, MY_HABITS_TAB);
        switchTabs(this, tabLayout, MY_HABITS_TAB);

        //add habit button action
        final FloatingActionButton addHabitButton = findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * call the add habit fragment
             * @param view
             */
            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");
            }
        });

        //Notifies if cloud data changes (from Firebase Interface)
        autoSnapshotListener(db, habitAdapter, HABIT_KEY);

    }

    /**
     * Adding a habit to the database and listview as the response to the user clicking the "Add" button from the fragment
     * @param newHabit
     */
    @Override
    public void onOkPressed(Habit newHabit) {
        //when the user clicks the add button, we want to add to the db and display the new entry
        if (newHabit.getTitle().length() > 0) {
            pushHabitData(db, newHabit);
        }
    }

    /**
     * To transfer the control to the Main activity/ homepage when the back button is pressed
     */
    public void onBackPressed() {
        Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}