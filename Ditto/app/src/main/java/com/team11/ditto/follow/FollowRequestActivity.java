package com.team11.ditto.follow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.team11.ditto.R;
import com.team11.ditto.UserProfileActivity;
import com.team11.ditto.interfaces.SwitchTabs;
import com.team11.ditto.profile_details.User;

import java.util.ArrayList;

/**
 * Activity to display a list of Users who are requesting to follow the ActiveUser,
 * which the User can accept or deny
 * TODO make able to accept/deny requests
 * @author Vivek Malhotra
 */
public class FollowRequestActivity extends AppCompatActivity implements SwitchTabs {

    //Declarations
    private TabLayout tabLayout;
    ListView frlist;
    private static FollowRequestList userAdapter;
    ArrayList<User> userDataList;

    /**
     * Instructions of what to do for Activity creation
     * Simple listview with tabs
     * @param savedInstanceState current app state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Set layouts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_request);
        frlist = findViewById(R.id.following_request_custom);
        tabLayout = findViewById(R.id.tabs);

        //Initialize values
        userDataList = new ArrayList<>();
        userAdapter = new FollowRequestList(FollowRequestActivity.this,userDataList);
        frlist.setAdapter(userAdapter);

        //TODO implement actual requests
        //For prototype display only
        User bruce = new User("Bruce Wayne","123456",49);
        userAdapter.add(bruce);

        //Enable tab switching
        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
    }

    /**
     * Define what to do if back pressed;
     * -goes back to ActiveUser profile
     */
    public void onBackPressed() {
        Intent intent = new Intent(FollowRequestActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
