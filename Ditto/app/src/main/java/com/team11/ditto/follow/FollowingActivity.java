/** Copyright [2021] [Reham Albakouni, Matt Asgari Motlagh, Aidan Horemans, Courtenay Laing-Kobe, Vivek Malhotra, Kelly Shih]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
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

/**
 * Activity to display a list of Users that follow the ActiveUser
 * @author Vivek Malhotra
 */
public class FollowingActivity extends AppCompatActivity implements SwitchTabs {

    //Declarations
    private TabLayout tabLayout;
    private ListView followingListView;
    private ArrayAdapter<User> userAdapter;
    private ArrayList<User> userDataList;

    /**
     * Instructions for Activity creation
     * Simple list view with tabs
     * @param savedInstanceState current app state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //set layouts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_list);
        followingListView = findViewById(R.id.following_list_custom);
        tabLayout = findViewById(R.id.tabs);

        //Initialize values
        userDataList = new ArrayList<>();
        userAdapter = new CustomListFollowerFollowing(FollowingActivity.this,userDataList);
        followingListView.setAdapter(userAdapter);

        //TODO Implement actual list of Users following the Active User
        //For prototype display only
        User user   = new User("Aryan", "12345678", 25);
        userAdapter.add(user);

        //Enable tab switching
        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);

        //View User profile if user in list is clicked
        onProfileClick();
    }

    /**
     * Define behavior if back button pressed
     * - goes back to ActiveUser profile
     */
    public void onBackPressed() {
        Intent intent = new Intent(FollowingActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * View a User in the list's profile if they are clicked
     */
    public void onProfileClick() {
        followingListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(FollowingActivity.this, FriendHabitActivity.class);
            startActivity(intent);
        });

    }

}
