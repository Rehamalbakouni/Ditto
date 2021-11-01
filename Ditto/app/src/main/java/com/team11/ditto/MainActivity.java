package com.team11.ditto;
/*
Class for Habit Event Activity, Feed/Homepage
Goals:
    To create an interface for these action because there is repetition between MyHabitActivity and the Homepage
 */
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

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements SwitchTabs, AddHabitEventFragment.OnFragmentInteractionListener {
    private static final String TAG = "tab switch";
    private TabLayout tabLayout;
    ListView habitEventListView;
    private ArrayList<HabitEvent> habitEventsData;
    private ArrayAdapter<HabitEvent> habitEventAdapter;
    private FirebaseFirestore db;
    HashMap<String, Object> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);

        habitEventListView = findViewById(R.id.list_habitevent);

        habitEventsData = new ArrayList<>();
        habitEventAdapter = new CustomListHabitEvent(MainActivity.this, habitEventsData);

        habitEventListView.setAdapter(habitEventAdapter);

        habitEventAdapter.add(new HabitEvent("hahahaha", "this is acomment", "", ""));

        currentTab(tabLayout, HOME_TAB);
        switchTabs(this, tabLayout, HOME_TAB);

        db = FirebaseFirestore.getInstance();
        //Get a top level reference to the collection
        final Query collectionReference = db.collection("HabitEvent")
                .orderBy("order");

        final FloatingActionButton addHabitEventButton = findViewById(R.id.add_habit_event);
        addHabitEventButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                new AddHabitEventFragment().show(getSupportFragmentManager(), "ADD_HABIT_EVENT");
            }
        });

        //Maintain listview after each activity switch, login, logout
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {

                // Clear the old list
                habitEventsData.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    Log.d(TAG, String.valueOf(doc.getData().get("habitID")));
                    String hID = (String) doc.getData().get("habitID");
                    String hComment = (String) doc.getData().get("comment");
                    String hPhoto = (String) doc.getData().get("photo");
                    String hLoc = (String) doc.getData().get("location");


                    habitEventsData.add(new HabitEvent(hID, hComment, hPhoto, hLoc)); // Adding the Habits from FireStore
                }
                habitEventAdapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });
    }

    @Override
    public void onOkPressed(HabitEvent newHabitEvent) {

        //when the user clicks the add button, we want to add to the db and display the new entry
        final String habitID = newHabitEvent.getHabitId();
        final String comment = newHabitEvent.getComment();
        final String photo = newHabitEvent.getPhoto();
        final String location = newHabitEvent.getLocation();

        //generate an auto-generated ID for firebase
        final DocumentReference documentReference = db.collection("HabitEvent").document();

        //get unique timestamp for ordering our list
        Date currentTime = Calendar.getInstance().getTime();
        data.put("habitID", habitID);
        data.put("comment", comment);
        data.put("photo", photo);
        data.put("location", location);

        //this field is used to add the current timestamp of the item, to be used to order the items
        data.put("order", currentTime);


        documentReference
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //method which gets executed when the task is successful
                        Log.d(TAG, "Data has been added successfully!");
                        //we want to add the habit event id to the associate Habit field of HabitEventIds

                        DocumentReference arrayID = db.collection("Habit").document(habitID);
                        //set the "habitEvents" field of the Habit
                        arrayID
                                .update("habitEvents", FieldValue.arrayUnion(documentReference.getId().toString()));


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