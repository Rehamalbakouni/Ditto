package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.team11.ditto.placeholder.PlaceholderContent.PlaceholderItem;
import com.team11.ditto.databinding.FragmentMyHabitBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHabitViewAdapter extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context context;

    public MyHabitViewAdapter(Context context, ArrayList<Habit> habits) {
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