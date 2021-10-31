package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FollowingActivity extends AppCompatActivity implements SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    ListView followingListView;
    private static ArrayAdapter<User> userAdapter;
    ArrayList<User> userDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_list);
        followingListView = findViewById(R.id.following_list_custom);
        tabLayout = findViewById(R.id.tabs);

        userDataList = new ArrayList<>();
        userAdapter = new FollowingList(FollowingActivity.this,userDataList);
        followingListView.setAdapter(userAdapter);
        User user   = new User("Aryan", "12345678", 25);
        User user1 = new User("Alessandro");
        userAdapter.add(user);
        userAdapter.add(user1);

        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
    }

    public void onBackPressed() {

        Intent intent = new Intent(FollowingActivity.this,UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
