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
import com.team11.ditto.UserProfileActivity;

import java.util.ArrayList;

/**
 * Activity to display a list of Users who follow the ActiveUser
 * @author Vivek Malhotra
 */
public class FollowerActivity extends AppCompatActivity implements SwitchTabs {

    //Declarations
    private TabLayout tabLayout;
    private ListView followingListView;
    private ArrayAdapter<User> userAdapter;
    private ArrayList<User> userDataList;

    /**
     * Instructions for creating activity
     * Simple listview with bottom tabs
     * @param savedInstanceState current app state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layouts
        setContentView(R.layout.follower_list);
        followingListView = findViewById(R.id.follower_list_custom);
        tabLayout = findViewById(R.id.tabs);

        //Initialize values
        userDataList = new ArrayList<>();
        userAdapter = new CustomListFollowerFollowing(FollowerActivity.this,userDataList);
        followingListView.setAdapter(userAdapter);

        //TODO implement actual ActiveUser follower list
        //For prototype only
        User user   = new User("Ezio Auditore da Firenze", "12345678", 25);
        userAdapter.add(user);

        //Enable tab switching
        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
       
    }

    /**
     * Define behaviour when back pressed;
     * Goes back to UserProfileActivity
     */
    public void onBackPressed() {
        Intent intent = new Intent(FollowerActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    
}
