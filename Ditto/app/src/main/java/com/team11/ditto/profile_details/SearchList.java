package com.team11.ditto.profile_details;

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

import java.util.ArrayList;

/**
 * Custom list view for User search to send follow requests
 * @author Vivek Malhotra
 */
public class SearchList extends ArrayAdapter<User> {

    //Declarations
    private ArrayList<User> users;
    private Context context;

    /**
     * Constructor for custom list
     * @param context activity context
     * @param users users to display
     */
    public SearchList(Context context, ArrayList<User> users) {
        super(context,0,users);
        this.users = users;
        this.context = context;
    }

    /**
     * Creates a view for a custom list item
     * @param position position in the list
     * @param convertView Android default
     * @param parent Android default
     * @return custom view for list item
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
         View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.search_content, parent,false);
        }

        User user = users.get(position);

        TextView username = view.findViewById(R.id.search_user_name);
        ImageView userphoto = view.findViewById(R.id.search_user_photo);

        username.setText(user.getUsername());
        //TODO implement actual profile photos
        userphoto.setImageResource(R.drawable.background);

        return view;
    }


}
