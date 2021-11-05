package com.team11.ditto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DueCustomList extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;

    public DueCustomList(Context context, ArrayList<User> users) {
        super(context,0,users);
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.duetoday_content, parent,false);
        }

        User user = users.get(position);

        TextView habitName = view.findViewById(R.id.due_habit_name);
        TextView habitDescription = view.findViewById(R.id.due_habit_description);
        ImageView progress = view.findViewById(R.id.my_progress);

        habitName.setText(user.getUsername());
        habitDescription.setText("Been missing gym for 2 years now");

        return view;
    }
}