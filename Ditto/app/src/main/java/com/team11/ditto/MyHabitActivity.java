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

import javax.annotation.Nullable;

/**To display the listview of Habits for a user in the "My Habits" tab
 *Allow a user to add a habit, swipe left to delete a habit
 * Goals:
 *     -A user can add a habit to the database, but cannot delete a habit from the db yet
 *     -Add the days of week to db
 *     -Allow user to edit an existing habit
 *     -Visually make it better
 * @author Kelly Shih, Aidan Horemans

 */
public class MyHabitActivity extends AppCompatActivity implements
        AddHabitFragment.OnFragmentInteractionListener, SwitchTabs, Firebase {

    private TabLayout tabLayout;
    //Declare variables for the list of habits
    //SwipeMenuListView habitListView;
    ListView habitListView;
    private static ArrayAdapter<Habit> habitAdapter;
    ArrayList<Habit> habitDataList;
    private FirebaseFirestore db;
    final String TAG = "add";

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


        habitAdapter = new CustomListHabit(MyHabitActivity.this, habitDataList);
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