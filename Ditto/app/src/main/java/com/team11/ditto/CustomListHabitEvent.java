package com.team11.ditto;
/*
Role: Initialize the Custom Listview Item's for the Feed Activity
Goal: To match the UI more accurately
    To have the custom item grow dynamically when there is a photo or location present
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Initialize the Custom Listview Item's for the Feed Activity
 * Goal: To match the UI more accurately
 *     To have the custom item grow dynamically when there is a photo or location present
 */
public class CustomListHabitEvent extends ArrayAdapter<HabitEvent> {
    private ArrayList<HabitEvent> habitEvents;
    private Context context;

    public CustomListHabitEvent(Context context, ArrayList<HabitEvent> habitEvents){
        super(context, 0, habitEvents);
        this.habitEvents = habitEvents;
        this.context = context;
    }

    /**
     * To return the view of the custom list item for the Homepage tab
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Nonnull
    @Override
    public View getView(int position, @Nullable View convertView,  @Nullable ViewGroup parent){
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_my_habit_event, parent, false);
        }

        HabitEvent habitEvent = habitEvents.get(position);

        TextView habitEventTitle = view.findViewById(R.id.firstLine);
        TextView habitEventComment = view.findViewById(R.id.secondLine);

        habitEventTitle.setText(habitEvent.getHabitId());
        habitEventComment.setText(habitEvent.getComment());

        return view;

    }


}
