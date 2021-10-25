package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity{
    FirebaseFirestore db; //when they addcity button we need to dump into db

    BottomNavigationView bottomNavigationView; //Navigation bar currently does nothing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = FirebaseFirestore.getInstance();
        // Get a top level reference to the collection
        final CollectionReference userReference = db.collection("User");
        final CollectionReference habitReference = db.collection("Habit");
        final CollectionReference habitEventRef = db.collection("HabitEvent");

        //we use a hashmap to store a key-value pair in firestore. Because its NoSQL database
        HashMap<String, String> data = new HashMap<>();


    }
}