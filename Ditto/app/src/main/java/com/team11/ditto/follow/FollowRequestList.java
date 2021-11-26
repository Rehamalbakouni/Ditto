/** Copyright [2021] [Reham Albakouni, Matt Asgari Motlagh, Aidan Horemans, Courtenay Laing-Kobe, Vivek Malhotra, Kelly Shih]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
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
import com.team11.ditto.profile_details.User;

import java.util.ArrayList;

/**
 * A custom listview adapter for Users who have requested to follow the ActiveUser
 * @author Vivek Malhotra, Courtenay Laing-Kobe
 */
public class FollowRequestList extends ArrayAdapter<User> {
    //Declarations
    private ArrayList<User> users;
    private final Context context;

    /**
     * Constructor for custom list adapter
     * @param context app context
     * @param users users to display
     */
    public FollowRequestList(Context context, ArrayList<User> users) {
        super(context,0,users);
        this.users = users;
        this.context = context;
    }

    /**
     * Create each list item view
     * @param position Android default
     * @param convertView Android default
     * @param parent Android default
     * @return View object containing custom layout for follow requests, for one user
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.follow_request_content, parent,false);
        }

        User user = users.get(position);

        TextView username = view.findViewById(R.id.fr_user_name);
        ImageView userPhoto = view.findViewById(R.id.fr_user_photo);

        username.setText(user.getUsername());
        userPhoto.setImageResource(R.drawable.background);

        return view;
    }

    /**
     * Method to add a User to the list
     * @param user User to add
     */
    public void add(User user){
        users.add(user);
    }

}
