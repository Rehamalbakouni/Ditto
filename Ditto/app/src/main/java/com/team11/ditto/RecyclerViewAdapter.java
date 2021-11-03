package com.team11.ditto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 *  Initialize the Custom RecyclerView item for the Habit Activity
 *  Goal: to match the UI more accurately
 * @author Kelly
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private ArrayList<Habit> courseDataArrayList;
    private Context mcontext;

    /**
     * creating a constructor class
     * @param recyclerDataArrayList
     * @param mcontext
     */
    public RecyclerViewAdapter(ArrayList<Habit> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    /**
     * To inflate the layout for the view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_habit, parent, false);
        return new RecyclerViewHolder(view);
    }

    /**
     * To set the data to textview from the Habit class
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Habit habit = courseDataArrayList.get(position);
        holder.habitTitle.setText(habit.getTitle());
        holder.habitReason.setText(habit.getReason());
    }

    /**
     * Returns the size of the recyclerview
     * @return
     */
    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }

    /**
     * Viewholder class to handle RecyclerView
     */
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView habitTitle;
        private TextView habitReason;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            habitTitle = itemView.findViewById(R.id.firstLine);
            habitReason = itemView.findViewById(R.id.secondLine);
        }
    }
}
