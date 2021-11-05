package com.team11.ditto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DueTodayActivity extends AppCompatActivity implements SwitchTabs {



    FirebaseFirestore db; //when they addcity button we need to dump into db
    private TabLayout tabLayout;
    private ListView list;
    private ArrayAdapter<User> dueTodayAdapter ;  // This needs to change to adapter of habits. For now, using user to check UI
    private ArrayList<User> user;                // This also needs to change '' '' '' ' '''''''

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        setContentView(R.layout.activity_due_today);
        // Go back to main activity ob back button press
        Calendar cal = Calendar.getInstance();
        Date today = Calendar.getInstance().getTime();
        String date = LocalDate.now().getDayOfWeek().toString();
        date = date + ", ";
        String month = LocalDate.now().getMonth().toString();
        date = date + month;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String dayOfMonthstr = String.valueOf(dayOfMonth);
        date = date + " " + dayOfMonthstr;
        setTitle(date);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        list = findViewById(R.id.due_today_custom_list);
        user = new ArrayList<>();
        dueTodayAdapter = new DueCustomList(DueTodayActivity.this, user);
        list.setAdapter(dueTodayAdapter);
        User user1 = new User("Mark","123456",26);
        dueTodayAdapter.add(user1);

        currentTab(tabLayout, DUE_TODAY_TAB);
        switchTabs(this, tabLayout, DUE_TODAY_TAB);


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}

