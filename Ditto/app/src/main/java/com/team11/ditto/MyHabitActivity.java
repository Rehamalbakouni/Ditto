package com.team11.ditto;
/*
The Class for MyHabit Activity Screen
 */

import android.content.Intent;
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
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;
    final String TAG = "addd";
    HashMap<String, String> data = new HashMap<>();



    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String string);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);

        habitDataList = new ArrayList<>();
        habitListView = findViewById(R.id.list);
        tabLayout = findViewById(R.id.tabs);


        habitAdapter = new CustomList_Habit(MyHabitActivity.this, habitDataList);
        habitListView.setAdapter(habitAdapter);

        Habit habit1 = new Habit("test", "reason", "hi");
        Habit habit2 = new Habit("second habit in the list", "this is the reason given for the habit", "02-02-02");

        habitAdapter.add(habit1);
        habitAdapter.add(habit2);

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
                    String docId = doc.getId();
                    String htitle = (String) doc.getData().get("title");
                    String hreason = (String) doc.getData().get("reason");
                    String hdate = (String) doc.getData().get("date_to_start");
                    habitDataList.add(new Habit(htitle, hreason, hdate)); // Adding the Habits from FireStore
                }
                habitAdapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });

    }

    @Override
    public void onOkPressed(Habit newHabit) {
        //when the user clicks the add button, we want to add to the db and display the new entry

        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final String date = newHabit.getDate();
        final CollectionReference collectionReference = db.collection("Habit");


        if (title.length()>0 && reason.length()>0) {
            //if there is some data in edittext field, then create new key-value pair
            data.put("title", title);
            data.put("reason", reason);
            data.put("date_to_start", date);

            //get unique document id. our format: habit title + current date, time added
            Date currentTime = Calendar.getInstance().getTime();

            collectionReference
                    .document(title)
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