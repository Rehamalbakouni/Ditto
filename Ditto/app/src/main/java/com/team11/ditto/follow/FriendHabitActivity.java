package com.team11.ditto.follow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.team11.ditto.R;
import com.team11.ditto.habit.Habit;
import com.team11.ditto.interfaces.SwitchTabs;

import java.util.ArrayList;

// TODO Important --- Need to fetch user's list of habits to show on screen
//  This is just a demo to see how this page would look like
// Need an active user for this page to work

/**
 * Activity to display a list of habits of a User the ActiveUser follows
 * TODO implement actual followed User's habits
 * @author Vivek Malhotra, Courtenay Laing-Kobe
 */
public class FriendHabitActivity extends AppCompatActivity implements SwitchTabs {

    //Declarations
    private TabLayout tabLayout;
    private ListView friendHabitList;
    private ArrayAdapter<Habit> friendHabitAdapter;
    private ArrayList<Habit> habitData;

    /**
     * Instructions on creating the Activity
     * Simple listview with bottom tabs
     * @param savedInstanceState current app state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Set layouts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        friendHabitList = findViewById(R.id.friend_habits);
        tabLayout = findViewById(R.id.tabs);

        //Initialize
        habitData = new ArrayList<>();
        friendHabitAdapter = new FriendHabitList(FriendHabitActivity.this, habitData);
        friendHabitList.setAdapter(friendHabitAdapter);

        //Enable tab switching
        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
    }

    /**
     * Define behaviour on back button press:
     * -Go back to FollowingActivity
     */
    public void onBackPressed() {
            Intent intent = new Intent(FriendHabitActivity.this, FollowingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        
}
