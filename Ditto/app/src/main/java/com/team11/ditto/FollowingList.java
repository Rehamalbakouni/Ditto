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

public class FollowingList extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;

    public FollowingList(Context context, ArrayList<User> users) {
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
            view = LayoutInflater.from(context).inflate(R.layout.follow_content, parent,false);
        }

        User user = users.get(position);

        TextView username = view.findViewById(R.id.user_name);
        ImageView userphoto = view.findViewById(R.id.user_photo);

        username.setText(user.getUsername());
        userphoto.setImageResource(R.drawable.background);

        return view;
    }
}
