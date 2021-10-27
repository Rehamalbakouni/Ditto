package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {



    private ImageView imageView;
    private TextView followers;
    private TextView no_followers;
    private TextView following;
    private TextView no_following;
    private TextView username_text;
    private TextView username;
    private Button search;
    private Button fr_pending;
    private Button logout;
    private static final String TAG = "tab switch";


    FirebaseFirestore db; //when they addcity button we need to dump into db
    private TabLayout tabLayout;
    //public static Bundle habitBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);


        tabLayout = findViewById(R.id.tabs);
        imageView =findViewById(R.id.imageView2);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        no_following = findViewById(R.id.no_following);
        no_followers = findViewById(R.id.no_followers);
        username_text = findViewById(R.id.textView_user);
        search = findViewById(R.id.search_users);
        username = findViewById(R.id.username_editText);
        fr_pending = findViewById(R.id.pending_fr);
        logout = findViewById(R.id.logout_button);
        currentTab();
        switchTabs();

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void switchTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // position 0 is for home
                if(tab.getPosition() ==0){
                    Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 1 is for MyHabits
                else if (tab.getPosition() == 1){
                    Intent intent = new Intent(UserProfileActivity.this,MyHabitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 2 is for Due Today
                else if (tab.getPosition() == 2){
                    Intent intent = new Intent(UserProfileActivity.this,DueToday.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                // position 3 is for Profile
                else if (tab.getPosition() == 3){
                    // DO NOTHING
                    ;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void currentTab(){
        TabLayout.Tab tab = (tabLayout).getTabAt(3);

        if (tab != null) {
            tab.select();
        }

    }
}
