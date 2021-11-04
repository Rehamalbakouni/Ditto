package com.team11.ditto;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;


public interface Firebase {

    String HABIT_KEY = "Habits";
    String USER_KEY = "Users";
    String HABIT_EVENT_KEY = "HabitEvent";
    String TAG = "add";
    HashMap<String, Object> data = new HashMap<>();
    ArrayList<Habit> habitList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<HabitEvent> eventList = new ArrayList<>();

    default void addToDB(FirebaseFirestore database, Context context, String key) {

        database.collection(key).get();

        Query collectionReference = createQuery(database, key);

        ArrayAdapter<?> dataAdapter = createAdapter(context, key);
    }

    default ArrayAdapter<?> createAdapter(Context context, String key) {
        switch (key) {
            case HABIT_KEY:
                return new CustomListHabit(context,habitList);
            case USER_KEY:
                return new CustomListUser(context, userList);
            case HABIT_EVENT_KEY:
                return new CustomListHabitEvent(context, eventList);
            default:
                throw new RuntimeException("Improper key used to access database");
        }
    }

    default ArrayList<?> keyList(String key){
        switch (key) {
            case HABIT_KEY:
                return habitList;
            case USER_KEY:
                return userList;
            case HABIT_EVENT_KEY:
                return eventList;
            default:
                throw new RuntimeException("Improper key used to access database");
        }
    }

    default Query createQuery(FirebaseFirestore db, String key) {
        return db.collection(key).orderBy("order");
    }

    default void logData(QuerySnapshot queryDocumentSnapshots, String key){
        for(QueryDocumentSnapshot doc: queryDocumentSnapshots) {

            switch (key) {
                case HABIT_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("title")));
                    String hTitle = (String) doc.getData().get("title");
                    String hReason = (String) doc.getData().get("reason");
                    ArrayList<Integer> hDate = (ArrayList<Integer>) doc.getData().get("days_of_week");
                    habitList.add(new Habit(hTitle, hReason, hDate));
                    break;
                case USER_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("username")));
                    String uUsername = (String) doc.getData().get("username");
                    String uPassword = (String) doc.getData().get("password");
                    int uAge = Integer.parseInt( (String) doc.getData().get("age"));
                    userList.add(new User(uUsername, uPassword, uAge));
                    break;
                case HABIT_EVENT_KEY:
                    Log.d(TAG, String.valueOf(doc.getData().get("habitID")));
                    String eHabitId = (String) doc.getData().get("habitID");
                    String eComment = (String) doc.getData().get("comment");
                    String ePhoto = (String) doc.getData().get("photo");
                    String eLocation = (String) doc.getData().get("location");
                    eventList.add(new HabitEvent(eHabitId, eComment, ePhoto, eLocation));
                    break;
                default:
                    throw new RuntimeException("Improper key used to access database");
            }
        }
    }

    default void autoSnapshotListener(Query query, ArrayAdapter<Object> adapter, String key){
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
}