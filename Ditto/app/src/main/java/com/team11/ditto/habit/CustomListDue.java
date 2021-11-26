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
package com.team11.ditto.habit;

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
 * Custom list to show Habits that are due on the current day of the week
 * @author Vivek Malhotra
 */
public class CustomListDue extends ArrayAdapter<Habit> {

    private ArrayList<Habit> habits;
    private Context context;

    public CustomListDue(Context context, ArrayList<Habit> habits) {
        super(context,0, habits);
        this.habits = habits;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.duetoday_content, parent,false);
        }

        Habit habit = habits.get(position);

        TextView habitName = view.findViewById(R.id.due_habit_name);
        TextView habitDescription = view.findViewById(R.id.due_habit_description);
        ImageView progress = view.findViewById(R.id.my_progress);

        habitName.setText(habit.getTitle());
        habitDescription.setText(habit.getReason());

        return view;
    }
}