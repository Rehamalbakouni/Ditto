package com.team11.ditto.follow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class FollowingActivity extends AppCompatActivity implements SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    private ListView followingListView;
    private ArrayAdapter<User> userAdapter;
    private ArrayList<User> userDataList;
    private int UserPosition;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_list);
        followingListView = findViewById(R.id.following_list_custom);
        tabLayout = findViewById(R.id.tabs);


        userDataList = new ArrayList<>();
        userAdapter = new CustomListFollowerFollowing(FollowingActivity.this,userDataList);
        followingListView.setAdapter(userAdapter);
        User user   = new User("Aryan", "12345678", 25);
        userAdapter.add(user);

        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
        onProfileClick();
    }

    public void onBackPressed() {

        Intent intent = new Intent(FollowingActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onProfileClick() {
        followingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserPosition = i;
                Intent intent = new Intent(FollowingActivity.this, FriendHabitActivity.class);

                startActivity(intent);
            }

        });

    }

}
