package com.team11.ditto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HabitEventRecyclerAdapter extends RecyclerView.Adapter<HabitEventRecyclerAdapter.ViewHolderEvent>{
    private ArrayList<HabitEvent> eventArrayList;
    private Context context;
    private EventClickListener eventClickListener;

    public HabitEventRecyclerAdapter(Context context, ArrayList<HabitEvent> eventArrayList, EventClickListener eventClickListener){
        this.eventArrayList = eventArrayList;
        this.context = context;
        this.eventClickListener = eventClickListener;
    }

    @Override
    public ViewHolderEvent onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_habit_event, parent, false);
        return new ViewHolderEvent(view, eventClickListener);
    }

    @Override
    public int getItemCount(){
        return eventArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvent holder, int position){
        HabitEvent habitEvent = eventArrayList.get(position);
        holder.habitEventTitle.setText(habitEvent.getHabitTitle());
        holder.habitEventComment.setText(habitEvent.getComment());

    }

    public class ViewHolderEvent extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView habitEventTitle;
        private TextView habitEventComment;
        EventClickListener eventClickListener;

        public ViewHolderEvent(@NonNull View itemView, EventClickListener eventClickListener){
            super(itemView);
            habitEventTitle = itemView.findViewById(R.id.firstLine);
            habitEventComment = itemView.findViewById(R.id.secondLine);
            this.eventClickListener = eventClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            eventClickListener.onEventClick(getBindingAdapterPosition());
            habitEventTitle = itemView.findViewById(R.id.firstLine);
            habitEventComment = itemView.findViewById(R.id.secondLine);

        }
    }

    public interface EventClickListener{
        void onEventClick(int position);
    }

}
