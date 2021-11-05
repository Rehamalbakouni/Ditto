package com.team11.ditto;
/*
Role: Class for Habit Event Activity, be able to see you feed and add a habit event
Goals:
    there is repetition between MyHabitActivity and the Homepage when creating fragments and listviews
    solve by making a more object oriented design
*/

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team11.ditto.habit_event.AddHabitEventFragment;
import com.team11.ditto.habit_event.HabitEvent;
import com.team11.ditto.habit_event.HabitEventRecyclerAdapter;
import com.team11.ditto.habit_event.ViewEventActivity;
import com.team11.ditto.interfaces.Firebase;
import com.team11.ditto.interfaces.SwitchTabs;
import com.team11.ditto.login.ActiveUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Role: Class for Habit Event Activity, be able to see you feed and add a habit event
 * TODO:
 *     there is repetition between MyHabitActivity and the Homepage when creating fragments and listviews
 *     solve by making a more object oriented design
 * @author: Kelly Shih, Aidan Horemans, Vivek Malhotra
 */

public class MainActivity extends AppCompatActivity implements SwitchTabs,
        AddHabitEventFragment.OnFragmentInteractionListener, Firebase,
        HabitEventRecyclerAdapter.EventClickListener {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    public static String EXTRA_HABIT_EVENT = "EXTRA_HABIT_EVENT";
    private ArrayList<HabitEvent> habitEventsData;

    private RecyclerView habitEventList;
    private HabitEventRecyclerAdapter habitEventRecyclerAdapter;

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

        // If device has userID, go to app - else, go to login
        if (new ActiveUser().getUID().equals("")) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(intent);
        }

        tabLayout = findViewById(R.id.tabs);

        setTitle("My Feed");

        habitEventList = findViewById(R.id.list_habit_event);
        habitEventsData = hEventsFirebase;

        habitEventRecyclerAdapter = new HabitEventRecyclerAdapter(this, habitEventsData, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        habitEventList.setLayoutManager(manager);
        habitEventList.setAdapter(habitEventRecyclerAdapter);

        currentTab(tabLayout, HOME_TAB);
        switchTabs(this, tabLayout, HOME_TAB);

        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection

        //Notifies if cloud data changes (from Firebase Interface)
        autoSnapshotListener(db, habitEventRecyclerAdapter, HABIT_EVENT_KEY);

        final FloatingActionButton addHabitEventButton = findViewById(R.id.add_habit_event);

        addHabitEventButton.setOnClickListener(view -> new AddHabitEventFragment()
                .show(getSupportFragmentManager(), "ADD_HABIT_EVENT"));
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

    // Check if device has stored userID
    private Boolean hasUserID() {

        LocalStorage ls = new LocalStorage(this);
        String uid = ls.getUserID();

        if (uid.equals(this.getString(R.string.null_user_id))) {
            return false;
        }
        return true;
    }

    /**
     * Handles the view Habit event activity
     * starts a new activity to view the clicked habit event
     * @param position of the clicked habit event
     */
    @Override
    public void onEventClick(int position) {
        habitEventsData.get(position);
        Intent intent = new Intent(this, ViewEventActivity.class);
        intent.putExtra(EXTRA_HABIT_EVENT, habitEventsData.get(position));
        startActivity(intent);
    }
}