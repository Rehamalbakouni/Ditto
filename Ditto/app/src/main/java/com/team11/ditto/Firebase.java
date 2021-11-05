package com.team11.ditto;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
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


public interface Firebase {

    String HABIT_KEY = "Habit";
    String USER_KEY = "User";
    String HABIT_EVENT_KEY = "HabitEvent";
    String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();
    ArrayList<Habit> habitsFirebase = new ArrayList<>();
    ArrayList<User> usersFirebase = new ArrayList<>();
    ArrayList<HabitEvent> hEventsFirebase = new ArrayList<>();

    //RecyclerViewAdapter
    default void autoSnapshotListener(FirebaseFirestore database, RecyclerViewAdapter adapter, String key){
        Query query = database.collection(key).orderBy("order");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            /**Maintain listview after each activity switch, login, logout
             *
             * @param queryDocumentSnapshots
             *          event data
             * @param error
             *          error data
             */
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {

                // Clear the old list
                keyList(key).clear();
                logData(queryDocumentSnapshots, key);
                adapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });
    }

    //HabitEventRecyclerAdapter
    default void autoSnapshotListener(FirebaseFirestore database, HabitEventRecyclerAdapter adapter, String key){
        Query query = database.collection(key).orderBy("order");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            /**Maintain listview after each activity switch, login, logout
             *
             * @param queryDocumentSnapshots
             *          event data
             * @param error
             *          error data
             */
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {

                // Clear the old list
                keyList(key).clear();
                logData(queryDocumentSnapshots, key);
                adapter.notifyDataSetChanged();
                // Notifying the adapter to render any new data fetched from the cloud
            }
        });
    }

    default void logData(@Nullable QuerySnapshot queryDocumentSnapshots, String key){
        if (queryDocumentSnapshots != null) {
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                switch (key) {
                    case HABIT_KEY:
                        Log.d(TAG, String.valueOf(doc.getData().get("title")));
                        String hTitle = (String) doc.getData().get("title");
                        String hReason = (String) doc.getData().get("reason");
                        ArrayList<Long> temp = (ArrayList<Long>) doc.getData().get("days_of_week");
                        ArrayList<Integer> hDate = new ArrayList<>();

                        Habit newHabit = new Habit(hTitle, hReason, hDate);
                        newHabit.setHabitID(doc.getId());

                        habitsFirebase.add(newHabit);

                        //TEMP FIX DO NOT LEAVE IN FINAL BUILD
                        //MAKES SURE ALL VALUES ARE INTS (problem with long being added to firebase)
                        if (temp != null && temp.size() > 0) {
                            for (int i = 0; i < temp.size(); i++) {
                                hDate.add(i, Integer.parseInt(String.valueOf(temp.get(i))));
                            }
                        }
                        break;

                    case USER_KEY:
                        Log.d(TAG, String.valueOf(doc.getData().get("username")));
                        String uUsername = (String) doc.getData().get("username");
                        String uPassword = (String) doc.getData().get("password");
                        int uAge = Integer.parseInt((String) doc.getData().get("age"));
                        usersFirebase.add(new User(uUsername, uPassword, uAge));
                        break;

                    case HABIT_EVENT_KEY:
                        Log.d(TAG, String.valueOf(doc.getData().get("habitID")));
                        String eHabitId = (String) doc.getData().get("habitID");
                        String eHabitTitle = (String) doc.getData().get("habitTitle");
                        String eComment = (String) doc.getData().get("comment");
                        String ePhoto = (String) doc.getData().get("photo");
                        String eLocation = (String) doc.getData().get("location");
                        hEventsFirebase.add(new HabitEvent(eHabitId, eComment, ePhoto, eLocation, eHabitTitle));
                        break;

                    default:
                        throw new RuntimeException("logData: Improper key used");
                }
            }
        }
    }

    default void pushHabitData(FirebaseFirestore database, Habit newHabit){
        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final ArrayList<Integer> dates = newHabit.getDate();
        Date currentTime = Calendar.getInstance().getTime();

        data.put("title", title);
        data.put("reason", reason);
        data.put("days_of_week", dates);
        //this field is used to add the current timestamp of the item, to be used to order the items
        data.put("order", currentTime);

        pushToDB(database, HABIT_KEY);
    }

    default void pushToDB(FirebaseFirestore database, String key){
        DocumentReference documentReference = database.collection(key).document();
        documentReference
                .set(data)
                .addOnSuccessListener(aVoid -> {
                    //method which gets executed when the task is successful
                    Log.d(TAG, "Data has been added successfully!");
                })
                .addOnFailureListener(e -> {
                    //method that gets executed if there's a problem
                    Log.d(TAG, "Data could not be added!" + e.toString());
                });
    }

    default ArrayList<?> keyList(String key){
        switch (key){
            case HABIT_KEY:
                return habitsFirebase;
            case USER_KEY:
                return usersFirebase;
            case HABIT_EVENT_KEY:
                return hEventsFirebase;
            default:
                throw new RuntimeException("keyList: Invalid key passed");
        }
    }

    default void pushHabitEventData(FirebaseFirestore database, HabitEvent newHabitEvent){
        String habitID = newHabitEvent.getHabitId();
        String comment = newHabitEvent.getComment();
        String photo = newHabitEvent.getPhoto();
        String location = newHabitEvent.getLocation();
        String habitTitle = newHabitEvent.getHabitTitle();

        //get unique timestamp for ordering our list
        Date currentTime = Calendar.getInstance().getTime();
        data.put("habitID", habitID);
        data.put("comment", comment);
        data.put("photo", photo);
        data.put("location", location);
        data.put("habitTitle", habitTitle);
        //this field is used to add the current timestamp of the item, to be used to order the items
        data.put("order", currentTime);

        pushToDB(database, HABIT_EVENT_KEY);
    }

    default void pushUserData(FirebaseFirestore database, User newUser) {
        data.put("username", newUser.getUsername());
        data.put("password", newUser.getPassword());
        data.put("age", newUser.getAge());

        pushToDB(database, USER_KEY);
    }

    default void getDocumentsHabit(FirebaseFirestore database, List<String> habits, List<String> habitsIDs, Spinner spinner, FragmentActivity fragmentActivity) {
        database.collection("Habit")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                            addHabitData(snapshot, habits, habitsIDs);
                        }
                        spinnerData(spinner, habits, fragmentActivity);
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

    }

    default void addHabitData(QueryDocumentSnapshot snapshot, List<String> habits, List<String> habitIDs) {
        Log.d(TAG, snapshot.getId() + "=>" + snapshot.getData());
        String habitTitle = snapshot.get("title").toString();
        String habitID = snapshot.getId();
        habits.add(habitTitle);
        habitIDs.add(habitID);
    }

    default void spinnerData(Spinner spinner, List<String> habits, FragmentActivity fragmentActivity) {
        //initialize the spinner with the options from the database
        ArrayAdapter<String> habitAdapter = new ArrayAdapter<>(fragmentActivity, android.R.layout.simple_spinner_item, habits);
        habitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(habitAdapter);
    }
}