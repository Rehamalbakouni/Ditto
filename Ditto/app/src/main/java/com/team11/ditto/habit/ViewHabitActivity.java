package com.team11.ditto.habit;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.team11.ditto.R;
import com.team11.ditto.interfaces.Firebase;

import java.util.ArrayList;

/**
 * Role: An Activity to view the contents of a chosen Habit.
 * Allow user to edit Habit and return updated data back to this activity
 * TODO: Get updated photos and locations updating in the database
 * @author Kelly Shih, Aidan Horemans
 */
public class ViewHabitActivity extends AppCompatActivity implements EditHabitFragment.OnFragmentInteractionListener, Firebase {

    TextView habitTitle; TextView habitReason; TextView habitDays;
    ArrayList<Integer> dates; String listDays;
    Habit selectedHabit;
    Bundle habitBundle;
    final String TAG = "view";
    private FirebaseFirestore database;

    /**
     * Create the dialog with the fields for reason, dates and go to OnOkPressed method when user clicks "Add"
     * TODO: get fields for photos and location
     * @param savedInstanceState current app state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit);
        habitReason = findViewById(R.id.habit_reason);
        habitDays = findViewById(R.id.habit_days);

        //Getting passed habit
        selectedHabit = (Habit) getIntent().getSerializableExtra("EXTRA_HABIT");

        //Setting title as habit title
        setTitle(selectedHabit.getTitle());

        //Setting habit_reason textview as habit reason
        habitReason.setText(selectedHabit.getReason());

        dates = selectedHabit.getDate();

        //Displaying dates in TextView
        if(dates != null){
            if(dates.size() > 0){
                listDays = "";
                for(int i = 0; i < dates.size(); i++){
                    listDays +=  intToDate(dates.get(i)) + " ";
                }
            }
        }

        habitDays.setText(listDays);
        habitTitle = findViewById(R.id.habit_tracking);
    }

    /**
     * Inflate the menu for the options menu
     * @param menu options menu
     * @return true when menu displayed, false otherwise
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Listener for the edit button
     * @param item selected item
     * @return true if displayed, false otherwise
     */
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

    /**
     * Updating a habit to the database and listview as the response to the user clicking the "Add" button from the fragment
     * @param habit habit to be updated
     */
    @Override
    public void onOkPressed(Habit habit) {

        //UPDATE THE OLD HABIT WITH THE NEW DATA

        //when the user clicks the add button, we want to add to the db and display the new entry
        final String title = habit.getTitle();
        final String reason = habit.getReason();
        final ArrayList<Integer> dates = habit.getDate();
        final String habitID = habit.getHabitID();
        Log.d(TAG, "dates -> "+ dates);

        database = FirebaseFirestore.getInstance();
        pushEditData(database, habit);

        //Updating old text with new habit stuff
        habitReason.setText(habit.getReason());

        if(dates != null){
            if(dates.size() > 0){
                listDays = "";
                for(int i = 0; i < dates.size(); i++){
                    listDays +=  intToDate(dates.get(i)) + " ";
                }
            }
        }

        habitDays.setText(listDays);
    }


    //Takes an integer and returns the respective day of the week,
    //returns null when given incorrect int
    //TODO enumerate dates to make this simpler
    private String intToDate(Integer date){
        if(date == 1){
            return "Monday";
        } else if (date == 2){
            return "Tuesday";
        } else if (date == 3){
            return "Wednesday";
        } else if (date == 4){
            return "Thursday";
        } else if (date == 5){
            return "Friday";
        } else if (date == 6){
            return "Saturday";
        } else if (date == 7){
            return "Sunday";
        }
        return null;
    }
}