package com.team11.ditto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team11.ditto.habit.CustomListDue;
import com.team11.ditto.habit.Habit;
import com.team11.ditto.interfaces.SwitchTabs;
import com.team11.ditto.login.ActiveUser;
import com.team11.ditto.profile_details.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Activity to display a list of the ActiveUser's Habits that are scheduled to be done today
 * @author Aidan Horemans, Kelly Shih, Vivek Malhotra
 */
public class DueTodayActivity extends AppCompatActivity implements SwitchTabs {
    FirebaseFirestore db;
    private TabLayout tabLayout;
    private ListView list;
    private ArrayAdapter<Habit> dueTodayAdapter ;  // This needs to change to adapter of habits. For now, using user to check UI
    private ArrayList<Habit> habits;                // This also needs to change '' '' '' ' '''''''
    private ActiveUser currentUser;
    private String TAG = "DueTodayActivity";

    /**
     *Directions for creating this Activity
     * Simple listview, bottom tabs
     * @param savedInstanceState current app state
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set layouts
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        db = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_due_today);
        tabLayout = findViewById(R.id.tabs);
        list = findViewById(R.id.due_today_custom_list);

        //Set title
        Calendar cal = Calendar.getInstance();
        String date = LocalDate.now().getDayOfWeek().toString();
        date = date + ", ";
        String month = LocalDate.now().getMonth().toString();
        date = date + month;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dayOfMonthstr = String.valueOf(dayOfMonth);
        date = date + " " + dayOfMonthstr;
        setTitle(date);

        habits = new ArrayList<Habit>();
        dueTodayAdapter = new CustomListDue(DueTodayActivity.this, habits);
        list.setAdapter(dueTodayAdapter);

        // Load habits
        currentUser = new ActiveUser();
        db.collection("Habit")
                .whereEqualTo("uid", currentUser.getUID())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        habits.clear();
                        for (QueryDocumentSnapshot document: value) {
                            String title = (String) document.getData().get("title");
                            String reason = (String) document.getData().get("reason");
                            ArrayList<Integer> days = (ArrayList<Integer>) document.getData().get("days_of_week");
                            Habit habit = new Habit(title, reason, days);
                            habits.add(habit);
                        }
                        dueTodayAdapter.notifyDataSetChanged();
                    }
                });

        currentTab(tabLayout, DUE_TODAY_TAB);
        switchTabs(this, tabLayout, DUE_TODAY_TAB);
    }

    /**
     * Define behaviour when back button pressed:
     * -go back to home page
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

