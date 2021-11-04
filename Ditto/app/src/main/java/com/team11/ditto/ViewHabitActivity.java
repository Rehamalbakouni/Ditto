package com.team11.ditto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ViewHabitActivity extends AppCompatActivity implements EditHabitFragment.OnFragmentInteractionListener{

    TextView habitTitle; TextView habitReason; TextView habitDays;
    ArrayList<Integer> dates; String title; String reason; String listDays;
    Habit selectedHabit;
    Bundle habitBundle;
    final String TAG = "view";
    private FirebaseFirestore database;
    HashMap<String, Object> data = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit);
        habitReason = findViewById(R.id.habit_reason);

        //Getting passed habit
        selectedHabit = (Habit) getIntent().getSerializableExtra("EXTRA_HABIT");

        //Setting title as habit title
        title = selectedHabit.getTitle();
        setTitle(title);

        //Setting habit_reason textview as habit reason
        reason = selectedHabit.getReason();
        habitReason.setText(reason);

        habitTitle = findViewById(R.id.habit_tracking);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Listener for edit button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.edit_habit){
            EditHabitFragment dialogFragment = new EditHabitFragment();

            //Creating bundle with selectedHabit
            habitBundle = new Bundle();
            habitBundle.putSerializable("HABIT", selectedHabit);

            //Passing bundle to EditHabitFragment
            dialogFragment.setArguments(habitBundle);

            //Opening EditHabitFragment with the selectedHabit bundled
            dialogFragment.show(getSupportFragmentManager(), "EDIT_HABIT");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOkPressed(Habit habit) {

        //UPDATE THE OLD HABIT WITH THE NEW DATA

        //when the user clicks the add button, we want to add to the db and display the new entry
        final String title = habit.getTitle();
        final String reason = habit.getReason();
        final ArrayList<Integer> dates = habit.getDate();
        final String habitID = habit.getHabitID();
        Log.d(TAG, "dates -> "+ dates);

        //THIS ONE LINE IS CAUSING PROBLEMS IDK WHY
        database = FirebaseFirestore.getInstance(); //<- you were missing this line
        final DocumentReference documentReference = database.collection("Habit").document(habitID);


        //get unique timestamp for ordering our list
        Date currentTime = Calendar.getInstance().getTime();
        data.put("title", title);
        data.put("reason", reason);
        data.remove("days_of_week"); //OTHERWISE DUPES GET ADDED
        data.put("days_of_week", dates);

        //this field is used to add the current timestamp of the item, to be used to order the items
        data.put("order", currentTime);


        documentReference
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //method which gets executed when the task is successful
                        Log.d(TAG, "Data has been added successfully!");
                        //we want to add the habit event id to the associate Habit field of HabitEventIds


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