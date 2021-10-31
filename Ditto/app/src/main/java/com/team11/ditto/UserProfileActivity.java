package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity implements SwitchTabs {

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
        no_following = findViewById(R.id.no_following_1);
        no_followers = findViewById(R.id.no_follower);
        username_text = findViewById(R.id.textView_user);
        search = findViewById(R.id.search_users);
        username = findViewById(R.id.username_editText);
        fr_pending = findViewById(R.id.pending_fr);
        logout = findViewById(R.id.logout_button);
        currentTab(tabLayout, PROFILE_TAB);
        switchTabs(this, tabLayout, PROFILE_TAB);
        onFollowingtap();
        onFollowNumberTap();
        no_following.setText("1");
        onSearchTap();
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onFollowingtap(){
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this,FollowingActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onFollowNumberTap(){
        no_following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this,FollowingActivity.class);
                startActivity(intent);
            }
        });;
    }

    public void onSearchTap(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this,SearchUserActivity.class);
                startActivity(intent);
            }
        });
    }

}
