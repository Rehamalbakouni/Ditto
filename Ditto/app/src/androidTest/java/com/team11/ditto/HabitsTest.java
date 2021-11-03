package com.team11.ditto;
/**
 * Test class for MyHabitActivity. All the UI tests are written here. Robotium test framework is
 used
 */
import static org.junit.Assert.*;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
public class HabitsTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MyHabitActivity> rule =
            new ActivityTestRule<>(MyHabitActivity.class, true, true);


    /**
     * Runs before all tests and creates solo instance
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity()) ;
    }

    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    /**
     * Add a habit to the listview and check title, reason, dates
     */
    @Test
    public void checkList() {
        // Asserts that the current activity is the MainActivity. Otherwise, show “Wrong Activity”
        solo.assertCurrentActivity("Wrong Activity", MyHabitActivity.class);
        View fab = (FloatingActionButton) solo.getView(R.id.add_habit);
        solo.clickOnView(fab); //Click to add habit
        //Get view for EditText and enter a habit title name
        solo.enterText((EditText) solo.getView(R.id.title_editText), "Running");
        solo.enterText((EditText) solo.getView(R.id.reason_editText), "Heart");
        solo.clickOnCheckBox(1);
        solo.clickOnCheckBox(5);
        solo.clickOnButton("ADD");

        /* True if there is a text: Edmonton on the screen, wait at least 2 seconds and find minimum one match. */
        assertTrue(solo.waitForText("Running", 1, 2000)); //if wait for text method returns true itll be true
        assertTrue(solo.waitForText("Heart", 1, 2000)); //if wait for text method returns true itll be true


    }
}
