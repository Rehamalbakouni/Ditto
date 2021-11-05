package com.team11.ditto.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.team11.ditto.R;
import com.team11.ditto.habit.Habit;
import com.team11.ditto.profile_details.User;

import java.util.ArrayList;

public class FriendHabitList extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context context;

    public FriendHabitList(Context context, ArrayList<Habit> habits) {
        super(context,0,habits);
        this.habits = habits;
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

        Habit habit = habits.get(position);

        TextView habitName = view.findViewById(R.id.friend_habit_name);
        TextView habitDescription = view.findViewById(R.id.friend_habit_description);
        ImageView progress = view.findViewById(R.id.friend_progress);

        habitName.setText(habit.getTitle());
        habitDescription.setText("This is a sample description of a habit");

        return view;
    }
}
