package com.team11.ditto.follow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.team11.ditto.R;
import com.team11.ditto.interfaces.SwitchTabs;
import com.team11.ditto.profile_details.User;

import java.util.ArrayList;

// Important --- Need to fetch user's list of habits to show on screen
//  This is just a demo to see how this page would look like
// Need an active user for this page to work
public class FriendHabitActivity extends AppCompatActivity implements SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    private ListView friendHabitList;
    private ArrayAdapter<User> friendHabitAdapter;
    private ArrayList<User> userData;
    private String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        friendHabitList = findViewById(R.id.friend_habits);
        tabLayout = findViewById(R.id.tabs);

        userData = new ArrayList<>();
        friendHabitAdapter = new FriendHabitList(FriendHabitActivity.this,userData);
        friendHabitList.setAdapter(friendHabitAdapter);

        userID = getIntent().getStringExtra("parent");
        User user   = new User("Bob the builder", "12345678", 25);
        User user1 = new User("Oggy and the Cockroaches", "123456",36);
        User user2 = new User("Dragon Ball z", "123456",100);
        User user3 = new User("Dexter's Laboratory", "123456", 26);
        User user4 = new User("Roadrunner","1234456",26);
        User user5 = new User("Johnny Bravo", "123456",26);
        User user6 = new User("Ed, Edd N Eddy", "123456",26);
        this.setTitle("Aryan");
        friendHabitAdapter.add(user);
        friendHabitAdapter.add(user1);
        friendHabitAdapter.add(user2);
        friendHabitAdapter.add(user3);
        friendHabitAdapter.add(user4);
        friendHabitAdapter.add(user5);
        friendHabitAdapter.add(user6);


        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
    }

    public void onBackPressed() {

            Intent intent = new Intent(FriendHabitActivity.this, FollowingActivity.class);

            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }





}
