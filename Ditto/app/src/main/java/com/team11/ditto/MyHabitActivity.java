package com.team11.ditto;
/*
The Class for MyHabit Activity Screen
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

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Nullable;

public class MyHabitActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener, SwitchTabs {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    SwipeMenuListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;
    final String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);

        habitDataList = new ArrayList<>();
        habitListView = (SwipeMenuListView) findViewById(R.id.list);
        tabLayout = findViewById(R.id.tabs);


        habitAdapter = new CustomList_Habit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        currentTab(tabLayout, MY_HABITS_TAB);
        switchTabs(this, tabLayout, MY_HABITS_TAB);


        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection
        final CollectionReference collectionReference = db.collection("Habit");

        //add habit button action
        final FloatingActionButton addHabitButton = findViewById(R.id.add_habit);
        addHabitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");

            }
        });

        //Maintain listview after each activity switch, login, logout
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
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

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
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
        };

        // set creator
        habitListView.setMenuCreator(creator);
        habitListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
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




    }

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


    public void onBackPressed() {
        Intent intent = new Intent(MyHabitActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




}