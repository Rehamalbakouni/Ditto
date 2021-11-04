package com.team11.ditto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<Habit> courseDataArrayList;
    private Context mcontext;
    private HabitClickListener habitClickListener;

    // creating a constructor class.
    public RecyclerViewAdapter(ArrayList<Habit> recyclerDataArrayList, Context mcontext, HabitClickListener habitClickListener) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.habitClickListener = habitClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_habit, parent, false);
        return new RecyclerViewHolder(view, habitClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Habit recyclerData = courseDataArrayList.get(position);
        holder.courseNameTV.setText(recyclerData.getTitle());
        holder.courseDescTV.setText(recyclerData.getReason());
    }

    @Override
    public int getItemCount() {
        // this method returns
        // the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // creating a variable for our text view.
        private TextView courseNameTV;
        private TextView courseDescTV;
        HabitClickListener habitClickListener;

        public RecyclerViewHolder(@NonNull View itemView, HabitClickListener habitClickListener) {
            super(itemView);
            // initializing our text views.
            courseNameTV = itemView.findViewById(R.id.firstLine);
            courseDescTV = itemView.findViewById(R.id.secondLine);
            this.habitClickListener = habitClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            habitClickListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface HabitClickListener {
        void onNoteClick(int position);
    }

}
