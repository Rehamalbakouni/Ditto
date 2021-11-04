package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FollowerActivity extends AppCompatActivity implements SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    private ListView followingListView;
    private ArrayAdapter<User> userAdapter;
    private ArrayList<User> userDataList;
    private int UserPosition;
    private String ParentActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_list);
        followingListView = findViewById(R.id.follower_list_custom);
        tabLayout = findViewById(R.id.tabs);
        ParentActivity = "Follower";
        userDataList = new ArrayList<>();
        userAdapter = new FollowingList(FollowerActivity.this,userDataList);
        followingListView.setAdapter(userAdapter);
        User user   = new User("Ezio Auditore da Firenze", "12345678", 25);
        userAdapter.add(user);

        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
        onProfileClick();
    }

    public void onBackPressed() {

        Intent intent = new Intent(FollowerActivity.this,UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onProfileClick() {
        followingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserPosition = i;
                Intent intent = new Intent(FollowerActivity.this, FriendHabitActivity.class);
                intent.putExtra("parent", ParentActivity);
                startActivity(intent);
            }

        });

    }
}
