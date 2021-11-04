package com.team11.ditto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FriendHabitList extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;

    public FriendHabitList(Context context, ArrayList<User> users) {
        super(context,0,users);
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.friend_profile_content, parent,false);
        }

        User user = users.get(position);

        TextView habitName = view.findViewById(R.id.friend_habit_name);
        TextView habitDescription = view.findViewById(R.id.friend_habit_description);
        ImageView progress = view.findViewById(R.id.friend_progress);

        habitName.setText(user.getUsername());
        habitDescription.setText("This is a sample description of a habit");

        return view;
    }
}
