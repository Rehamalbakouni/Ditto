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

public class FollowRequestActivity extends AppCompatActivity implements SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    ListView frlist;
    private static ArrayAdapter<User> userAdapter;
    ArrayList<User> userDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_request);
        frlist = findViewById(R.id.following_request_custom);
        tabLayout = findViewById(R.id.tabs);

        userDataList = new ArrayList<>();
        userAdapter = new FollowRequestList(FollowRequestActivity.this,userDataList);
        frlist.setAdapter(userAdapter);

        User bruce = new User("Bruce Wayne","123456",49);

        userAdapter.add(bruce);

        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
    }

    public void onBackPressed() {

        Intent intent = new Intent(FollowRequestActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
