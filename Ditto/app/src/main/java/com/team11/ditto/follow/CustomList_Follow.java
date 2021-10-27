package com.team11.ditto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList_Follow extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;

    public CustomList_Follow(Context context, ArrayList<User> users, int typeCode) {
        super(context, 0, users);
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null && typeCode == FOLLOWING_USERS){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_following, parent,false);
        }

        Habit habit = habits.get(position);

        TextView habitTitle = view.findViewById(R.id.firstLine);
        TextView habitReason = view.findViewById(R.id.secondLine);

        habitTitle.setText(habit.getTitle());
        habitReason.setText(habit.getReason());

        return view;
    }
}

