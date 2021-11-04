package com.team11.ditto;
/*
Role: To display the listview of Habits for a user in the "My Habits" tab
Allow a user to add a habit, swipe left to delete a habit
Goals:
    -Implement occurrence tracking
    -Visually make it better
 */

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**To display the listview of Habits for a user in the "My Habits" tab
 *Allow a user to add a habit, swipe left to delete a habit
 * TODO:
 *     -A user can add a habit to the database, but cannot delete a habit from the db yet
 *     -Add the days of week to db
 *     -Allow user to edit an existing habit
 *     -Visually make it better
 *     -Get the happy faces for the level of completion for each habit
 *     -WHEN YOU DELETE A HABIT, ALSO DELETE THE HABIT EVENT ITS ASSOCIATED WITH
 * @author Kelly Shih, Aidan Horemans

 */
public class MyHabitActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener, SwitchTabs, RecyclerViewAdapter.HabitClickListener {

    public static String EXTRA_HABIT = "EXTRA_HABIT";
    private TabLayout tabLayout;

    //Declare variables for the list of habits
    private RecyclerView habitListView;

    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Habit> habitDataList;

    private FirebaseFirestore db;
    final String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();

    /**
     * Create the Activity instance for the "My Habits" screen, control flow of actions
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habit);
        tabLayout = findViewById(R.id.tabs);

        habitDataList = new ArrayList<>();
        habitListView = (RecyclerView) findViewById(R.id.list);

        recyclerViewAdapter = new RecyclerViewAdapter(habitDataList, this, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        habitListView.setLayoutManager(manager);
        habitListView.setAdapter(recyclerViewAdapter);

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
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d(TAG, String.valueOf(doc.getData().get("title")));
                    String htitle = (String) doc.getData().get("title");
                    String hreason = (String) doc.getData().get("reason");
                    ArrayList<Long> temp = (ArrayList<Long>) doc.getData().get("days_of_week");
                    ArrayList<Integer> hdate = new ArrayList<>();

                    //TEMP FIX DO NOT LEAVE IN FINAL BUILD
                    //MAKES SURE ALL VALUES ARE INTS (problem with long being added to firebase)
                    if (temp.size() > 0) {
                        for (int i = 0; i < temp.size(); i++) {
                            hdate.add(i, Integer.parseInt(String.valueOf(temp.get(i))));
                        }
                    }

                    Habit newHabit = new Habit(htitle, hreason, hdate);
                    newHabit.setHabitID(doc.getId());
                    habitDataList.add(newHabit); // Adding the Habits from FireStore

                }
                recyclerViewAdapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(habitListView);
    }

    /**
     * Adding a habit to the database and listview as the response to the user clicking the "Add" button from the fragment
     *
     * @param newHabit
     */
    @Override
    public void onOkPressed(Habit newHabit) {
        //when the user clicks the add button, we want to add to the db and display the new entry

        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final ArrayList<Integer> dates = newHabit.getDate();
        //final ArrayList<String> habitEventslist = new ArrayList<String>();

        //generate an auto-generated ID for firebase
        final DocumentReference documentReference = db.collection("Habit").document();

        //get unique timestamp for ordering our list
        Date currentTime = Calendar.getInstance().getTime();

        if (title.length() > 0) {
            //if there is some data in edittext field, then create new key-value pair
            data.put("title", title);
            data.put("reason", reason);
            data.put("days_of_week", dates);
            //data.put("habitEvents", habitEventslist);
            //this field is used to add the current timestamp of the item, to be used to order the items
            data.put("order", currentTime);

            documentReference
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //method which gets executed when the task is successful
                            Log.d(TAG, "Data has been added successfully!");
                            newHabit.setHabitID(documentReference.getId());

                            for (int i = 0; i < dates.size(); i++) {
                                DocumentReference arrayID = db.collection("Habit").document(documentReference.getId());
                                Log.d(TAG, "DOC REFERENCE " + documentReference.getId());
                                //set the "days_of_week"
                                arrayID.update("days_of_week", FieldValue.arrayUnion(dates.get(i)));
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
        Intent intent = new Intent(MyHabitActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        /**
         * To delete an item from the listview and database when a Habit is swiped to the left
         * @param recyclerView
         * @param viewHolder
         * @param target
         * @return
         */
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        /**
         * When an item is swiped left, delete from database and recyclerview
         * @param viewHolder
         * @param direction
         */
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Habit oldEntry = (Habit) habitDataList.get(viewHolder.getAbsoluteAdapterPosition());
            habitDataList.remove(viewHolder.getAbsoluteAdapterPosition());
            recyclerViewAdapter.notifyDataSetChanged();

            //ALSO REMOVE THE ASSOCIATED HABIT EVENTS
            db.collection("Habit").document(oldEntry.getHabitID()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            ArrayList<String> habitEventIds = (ArrayList<String>) document.get("habitEvents");
                            if (habitEventIds != null) {
                                deleteHabitEvents(habitEventIds);
                            }

                            deleteHabit(oldEntry);
                        }
                    }
                }

            });

        }

        /**
         * To set the background color and background icon for a swipe to delete item in the list.
         * @param c
         * @param recyclerView
         * @param viewHolder
         * @param dX
         * @param dY
         * @param actionState
         * @param isCurrentlyActive
         */
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //Get recyclerView item from viewholder
            View itemView = viewHolder.itemView;
            ColorDrawable background = new ColorDrawable();
            background.setColor(Color.rgb(0xC9, 0xC9,
                    0xCE));
            background.setBounds((int) (itemView.getRight() + dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
            background.draw(c);

            Drawable deleteIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_round_delete_24);
            int itemTop = itemView.getTop() + ((itemView.getBottom() - itemView.getTop()) - deleteIcon.getIntrinsicHeight()) / 2;
            int itemMargin = ((itemView.getBottom() - itemView.getTop()) - deleteIcon.getIntrinsicHeight()) / 2;
            int itemLeft = itemView.getRight() - itemMargin - deleteIcon.getIntrinsicWidth();
            int itemRight = itemView.getRight() - itemMargin;
            int itemBottom = itemTop + deleteIcon.getIntrinsicHeight();
            deleteIcon.setBounds(itemLeft, itemTop, itemRight, itemBottom);
            deleteIcon.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    /**
     * Opens ViewHabitActivity to view and potentially update the clicked object
     *
     * @param position
     */
    @Override
    public void onHabitClick(int position) {
        habitDataList.get(position);
        Intent intent = new Intent(this, ViewHabitActivity.class);
        intent.putExtra(EXTRA_HABIT, habitDataList.get(position));
        startActivity(intent);
    }

    /**
     * If the array is not null, go to this function
     */
    public void deleteHabitEvents(ArrayList<String> habitEventIds) {
        for (int i = 0; i < habitEventIds.size(); i++) {
            //delete the associated habit event in the database
            Log.d(TAG, "habiteventid " + habitEventIds.get(i));
            db.collection("HabitEvent").document(habitEventIds.get(i))
                    .delete();
        }
    }

        public void deleteHabit(Habit oldEntry){
            //remove from database
            db.collection("Habit").document(oldEntry.getHabitID())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document", e);
                        }
                    });
        }
}