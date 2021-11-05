package com.team11.ditto.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team11.ditto.R;

import java.util.ArrayList;

/**
 *  Initialize the Custom RecyclerView item for the Habit Activity
 *  TODO: to match the UI more accurately
 * @author Kelly
 */
public class HabitRecyclerAdapter extends RecyclerView.Adapter<HabitRecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<Habit> courseDataArrayList;
    private Context mcontext;
    private HabitClickListener habitClickListener;

    /**
     * creating a constructor class
     * @param recyclerDataArrayList
     * @param mcontext
     */
    public HabitRecyclerAdapter(ArrayList<Habit> recyclerDataArrayList, Context mcontext, HabitClickListener habitClickListener) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.habitClickListener = habitClickListener;
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
        return new RecyclerViewHolder(view, habitClickListener);
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

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // creating a variable for our text view.
        private TextView habitTitle;
        private TextView habitReason;
        HabitClickListener habitClickListener;

        public RecyclerViewHolder(@NonNull View itemView, HabitClickListener habitClickListener) {
            super(itemView);
            // initializing our text views.
            habitTitle = itemView.findViewById(R.id.firstLine);
            habitReason = itemView.findViewById(R.id.secondLine);
            this.habitClickListener = habitClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            habitClickListener.onHabitClick(getBindingAdapterPosition());
            habitTitle = itemView.findViewById(R.id.firstLine);
            habitReason = itemView.findViewById(R.id.secondLine);
        }


    }
    public interface HabitClickListener {
        void onHabitClick(int position);
    }

}
