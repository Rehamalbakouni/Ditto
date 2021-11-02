package com.team11.ditto;
/*
Role: Initialize the Custom Listview Item's for the Habit Activity
Goal: To match the UI more accurately
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Initialize the Custom Listview Item's for the Habit Activity
 * Goal: to match the UI more accurately
 */
public class CustomList_Habit extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context context;

    public CustomList_Habit(Context context, ArrayList<Habit> habits) {
        super(context, 0, habits);
        this.habits = habits;
        this.context = context;
    }

    /**
     * To return the view of the custom list item for the Habits tab
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_my_habit, parent,false);
        }

        Habit habit = habits.get(position);

        TextView habitTitle = view.findViewById(R.id.firstLine);
        TextView habitReason = view.findViewById(R.id.secondLine);

        habitTitle.setText(habit.getTitle());
        habitReason.setText(habit.getReason());

        return view;
    }
}
