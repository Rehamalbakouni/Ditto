package com.team11.ditto.habit_event;

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
 * Custom RecyclerViewAdapter for HabitEvents
 * @author Kelly Shih, Aidan Horemans
 */
public class HabitEventRecyclerAdapter extends RecyclerView.Adapter<HabitEventRecyclerAdapter.ViewHolderEvent>{
    //Declarations
    private ArrayList<HabitEvent> eventArrayList;
    private Context context;
    private EventClickListener eventClickListener;

    /**
     * Constructor
     * @param context activity context
     * @param eventArrayList list of HabitEvents
     * @param eventClickListener listener for interaction
     */
    public HabitEventRecyclerAdapter(Context context, ArrayList<HabitEvent> eventArrayList, EventClickListener eventClickListener){
        this.eventArrayList = eventArrayList;
        this.context = context;
        this.eventClickListener = eventClickListener;
    }

    /**
     * Create a holder for the view
     * @param parent Android default
     * @param viewType Android default
     * @return view holder
     */
    @Override
    public ViewHolderEvent onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_habit_event, parent, false);
        return new ViewHolderEvent(view, eventClickListener);
    }

    /**
     * Get list size
     * @return number of items
     */
    @Override
    public int getItemCount(){
        return eventArrayList.size();
    }

    /**
     * Connect holders to habit attributes for display
     * @param holder view holder
     * @param position list position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvent holder, int position){
        HabitEvent habitEvent = eventArrayList.get(position);
        holder.habitEventTitle.setText(habitEvent.getHabitTitle());
        holder.habitEventComment.setText(habitEvent.getComment());
    }

    /**
     * Class TODO Please finish this I have no idea what the point of this is ^^;;
     */
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
