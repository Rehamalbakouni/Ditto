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

public class CustomList_Habit extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context context;

    public CustomList_Habit(MyHabitActivity context, ArrayList<Habit> habits) {
        super(context, 0, habits);
        this.habits = habits;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content_habit, parent,false);
        }

        Habit habit = habits.get(position);

        TextView habitTitle = view.findViewById(R.id.content_habit_title);
        TextView habitReason = view.findViewById(R.id.content_habit_reason);

        habitTitle.setText(habit.getTitle());
        habitReason.setText(habit.getReason());

        return view;
    }
}
