package com.team11.ditto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team11.ditto.habit.CustomListDue;
import com.team11.ditto.habit.Habit;
import com.team11.ditto.interfaces.SwitchTabs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Activity to display a list of the ActiveUser's Habits that are scheduled to be done today
 * @author Aidan Horemans, Kelly Shih, Vivek Malhotra
 */
public class DueTodayActivity extends AppCompatActivity implements SwitchTabs {
    FirebaseFirestore db;
    private TabLayout tabLayout;
    private ListView list;
    private ArrayAdapter<Habit> dueTodayAdapter ;
    private ArrayList<Habit> habits;

    /**
     *Directions for creating this Activity
     * Simple listview, bottom tabs
     * @param savedInstanceState current app state
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set layouts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_today);
        tabLayout = findViewById(R.id.tabs);
        list = findViewById(R.id.due_today_custom_list);

        //Set title
        Calendar cal = Calendar.getInstance();
        String date = LocalDate.now().getDayOfWeek().toString();
        date = date + ", ";
        String month = LocalDate.now().getMonth().toString();
        date = date + month;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dayOfMonthstr = String.valueOf(dayOfMonth);
        date = date + " " + dayOfMonthstr;
        setTitle(date);

        //Initialize
        habits = new ArrayList<>();
        dueTodayAdapter = new CustomListDue(DueTodayActivity.this, habits);
        list.setAdapter(dueTodayAdapter);

        //For prototype display only
        //TODO implement actual due today list
        ArrayList<Integer> dates1 = new ArrayList<>();
        dates1.add(0);
        Habit habit1 = new Habit("Go to the Gym","",dates1);
        dueTodayAdapter.add(habit1);

        //Enable tab switching
        currentTab(tabLayout, DUE_TODAY_TAB);
        switchTabs(this, tabLayout, DUE_TODAY_TAB);
    }

    /**
     * Define behaviour when back button pressed:
     * -go back to home page
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

