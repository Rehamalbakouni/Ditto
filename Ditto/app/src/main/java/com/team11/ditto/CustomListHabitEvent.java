package com.team11.ditto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CustomListHabitEvent extends ArrayAdapter<String> {
    private ArrayList<String> habitEvents;
    private Context context;

    public CustomListHabitEvent(Context context, ArrayList<String> habitEvents){
        super(context, 0, habitEvents);
        this.habitEvents = habitEvents;
        this.context = context;
    }

    @Nonnull
    @Override
    public View getView(int position, @Nullable View convertView,  @Nullable ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_my_habit_event, parent, false);
        }

        return view;

    }


}
