package com.team11.ditto.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.team11.ditto.R;
import com.team11.ditto.User;

import java.util.ArrayList;

public class CustomList_Follow extends ArrayAdapter<User> implements UserCodes{

    private ArrayList<User> users;
    private Context context;

    public CustomList_Follow(Context context, ArrayList<User> users) {
        super(context, 0, users);
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_follow, parent,false);
        }

        User user = users.get(position);

        TextView userName = view.findViewById(R.id.user_name);
        ImageView userPhoto = view.findViewById(R.id.user_photo);

        userName.setText(user.getUsername());
        //userPhoto.setImageDrawable(user.getPhoto());

        return view;
    }
}

