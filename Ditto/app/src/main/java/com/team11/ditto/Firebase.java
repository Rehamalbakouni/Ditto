package com.team11.ditto;

import android.util.Log;
import android.widget.ArrayAdapter;

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

import javax.annotation.Nullable;


public interface Firebase {

    String HABIT_KEY = "Habits";
    String USER_KEY = "Users";
    String HABIT_EVENT_KEY = "HabitEvent";
    String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();
    ArrayList<Habit> habitsFirebase = new ArrayList<>();
    ArrayList<User> usersFirebase = new ArrayList<>();
    ArrayList<HabitEvent> hEventsFirebase = new ArrayList<>();

    default void autoSnapshotListener(FirebaseFirestore database, ArrayAdapter<?> adapter, String key){
        Query query = database.collection(key).orderBy("order");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            /**Maintain listview after each activity switch, login, logout
             *
             * @param queryDocumentSnapshots
             * @param error
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

    default void logData(QuerySnapshot queryDocumentSnapshots, String key){
        for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {

            switch (key) {
                case HABIT_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("title")));
                    String hTitle = (String) doc.getData().get("title");
                    String hReason = (String) doc.getData().get("reason");
                    ArrayList<Integer> hDate = (ArrayList<Integer>) doc.getData().get("days_of_week");
                    habitsFirebase.add(new Habit(hTitle, hReason, hDate));
                    break;
                case USER_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("username")));
                    String uUsername = (String) doc.getData().get("username");
                    String uPassword = (String) doc.getData().get("password");
                    int uAge = Integer.parseInt( (String) doc.getData().get("age"));
                    usersFirebase.add(new User(uUsername, uPassword, uAge));
                    break;
                case HABIT_EVENT_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("habitID")));
                    String eHabitId = (String) doc.getData().get("habitID");
                    String eComment = (String) doc.getData().get("comment");
                    String ePhoto = (String) doc.getData().get("photo");
                    String eLocation = (String) doc.getData().get("location");
                    hEventsFirebase.add(new HabitEvent(eHabitId, eComment, ePhoto, eLocation));
                    break;
                default:
                    throw new RuntimeException("logData: Improper key used");
            }
        }
    }

    default void putHabitData(Habit newHabit){
        final String title = newHabit.getTitle();
        final String reason = newHabit.getReason();
        final ArrayList<Integer> dates = newHabit.getDate();
        Date currentTime = Calendar.getInstance().getTime();

        if (title.length() > 0) {
            //if there is some data in edittext field, then create new key-value pair
            data.put("title", title);
            data.put("reason", reason);
            data.put("days_of_week", dates);
            //this field is used to add the current timestamp of the item, to be used to order the items
            data.put("order", currentTime);
/*
            for (int i = 0; i < dates.size(); i++) {
                DocumentReference arrayID = database.collection("Habit").document(documentReference.getId());
                Log.d(TAG, "DOC REFERENCE " + documentReference.getId());
                //set the "days_of_week"
                arrayID
                        .update("days_of_week", FieldValue.arrayUnion(dates.get(i)));
            }*/
        }
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
}