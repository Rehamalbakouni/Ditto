package com.team11.ditto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

        FragmentManager manager = getActivity().getSupportFragmentManager();

        followers.setOnClickListener(view1 -> manager.beginTransaction()
                .replace(R.id.navigation_header_container, new ViewListActivity())
                .commit());

        following.setOnClickListener(view12 -> {
        });

        followers.setOnClickListener(view13 -> {
        });

        logout.setOnClickListener(view14 -> {
        });

        userSearch.setOnClickListener(view15 -> {
        });

        pendingFollow.setOnClickListener(view16 -> {
        });


    }

}
