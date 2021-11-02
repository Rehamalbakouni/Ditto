package com.team11.ditto;
/*
Role: To display the listview of Habits for a user in the "My Habits" tab
Allow a user to add a habit, swipe left to delete a habit
Goals:
    -A user can add a habit to the database, but cannot delete a habit from the db yet
    -Add the days of week to db
    -Allow user to edit an existing habit
    -Visually make it better
 */

/*
Permissions for the swipe function for a listview item:
https://github.com/baoyongzhang/SwipeMenuListView
The MIT License (MIT)

Copyright (c) 2014 baoyongzhang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

/**To display the listview of Habits for a user in the "My Habits" tab
 *Allow a user to add a habit, swipe left to delete a habit
 * Permissions for the swipe function for a listview item:
 * https://github.com/baoyongzhang/SwipeMenuListView
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 baoyongzhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * @author Kelly Shih, Aidan Horemans

 */
public class MyHabitActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener, SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    //SwipeMenuListView habitListView;
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;
    final String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();

    /**
     * Create the Activity instance for the "My Habits" screen, control flow of actions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);

        habitDataList = new ArrayList<>();
        habitListView = (ListView) findViewById(R.id.list);
        tabLayout = findViewById(R.id.tabs);


        habitAdapter = new CustomList_Habit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        currentTab(tabLayout, MY_HABITS_TAB);
        switchTabs(this, tabLayout, MY_HABITS_TAB);


        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection
        final Query collectionReference = db.collection("Habit")
                .orderBy("order");

        //add habit button action
        final FloatingActionButton addHabitButton = findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * call the add habit fragment
             * @param view
             */
            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");

            }
        });

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            /**Maintain listview after each activity switch, login, logout
             *
             * @param queryDocumentSnapshots
             * @param error
             */
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {

                // Clear the old list
                habitDataList.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    Log.d(TAG, String.valueOf(doc.getData().get("title")));
                    String htitle = (String) doc.getData().get("title");
                    String hreason = (String) doc.getData().get("reason");
                    ArrayList<Integer> hdate = (ArrayList<Integer>) doc.getData().get("days_of_week");
                    habitDataList.add(new Habit(htitle, hreason, hdate)); // Adding the Habits from FireStore
                }
                habitAdapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });
        /*
        SwipeMenuCreator creator = new SwipeMenuCreator() {*/
            /**
             * initialize the swipe menu for a list view object (we only have a delete option)
             * @param menu
             */
        /*    @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                deleteItem.setWidth(169);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_round_delete_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };*/
        /*
        // set creator
        habitListView.setMenuCreator(creator);
        habitListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            /**
             * After the user CLICKS on the garbage can, we want to delete from listview and database
             * @param position
             * @param menu
             * @param index
             * @return
             */
        /*
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                // delete
                habitDataList.remove(position);
                habitAdapter.notifyDataSetChanged();
                //DELETE FROM DATABASE TOO
                //db.collection("Habit").document()

                // false : close the menu; true : not close the menu
                return false;
            }
        });


        //set Left direction
        habitListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

         */




    }

    /**
     * Adding a habit to the database and listview as the response to the user clicking the "Add" button from the fragment
     * @param newHabit
     */
    @Override
    public void onOkPressed(Habit newHabit) {
        //when the user clicks the add button, we want to add to the db and display the new entry

        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final ArrayList<Integer> dates = newHabit.getDate();

        //generate an auto-generated ID for firebase
        final DocumentReference documentReference = db.collection("Habit").document();

        //get unique timestamp for ordering our list
        Date currentTime = Calendar.getInstance().getTime();

        if (title.length()>0) {
            //if there is some data in edittext field, then create new key-value pair
            data.put("title", title);
            data.put("reason", reason);
            data.put("days_of_week", dates);
            //this field is used to add the current timestamp of the item, to be used to order the items
            data.put("order", currentTime);

            documentReference
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //method which gets executed when the task is successful
                            Log.d(TAG, "Data has been added successfully!");
                            Log.d(TAG, "size of dates " + dates.size());

                            for (int i = 0; i < dates.size(); i++) {
                                DocumentReference arrayID = db.collection("Habit").document(documentReference.getId());
                                Log.d(TAG, "DOC REFERENCE " + documentReference.getId());
                                //set the "days_of_week"
                                arrayID
                                        .update("days_of_week", FieldValue.arrayUnion(dates.get(i)));
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //method that gets executed if there's a problem
                            Log.d(TAG, "Data could not be added!" + e.toString());

                        }
                    });
        }


    }

    /**
     * To transfer the control to the Main activity/ homepage when the back button is pressed
     */
    public void onBackPressed() {
        Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




}