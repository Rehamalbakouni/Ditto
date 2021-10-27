package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ProfileFragment extends Fragment {
    public ProfileFragment(){
        super(R.layout.fragment_profile);
    }

    private Button logout;
    private Button userSearch;
    private Button pendingFollow;
    private TextView followers;
    private TextView following;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        logout = view.findViewById(R.id.logout_button);
        userSearch = view.findViewById(R.id.search_followers);
        pendingFollow = view.findViewById(R.id.pending_fr);

        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("");
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        userSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        pendingFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }

}
