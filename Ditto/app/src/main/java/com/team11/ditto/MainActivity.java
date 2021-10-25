package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

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

        //Navigation between all the different bottom options
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment_container);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        NavController navController = Navigation.findNavController(this, R.id.navigation_fragment_container);
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

}