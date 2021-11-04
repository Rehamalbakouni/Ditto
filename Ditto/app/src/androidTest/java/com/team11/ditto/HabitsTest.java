package com.team11.ditto;
/**
 * Test class for MyHabitActivity. All the UI tests are written here. Espresso test framework is
 used
 */
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HabitsTest {

    @Rule
    public ActivityScenarioRule<MyHabitActivity> activityRule = new ActivityScenarioRule<>(MyHabitActivity.class);

    @Test
    public void testAddHabit() {
        String title = "Running";
        String reason = "Get healthy";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.title_editText)).perform(typeText(title));
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        onView(withId(R.id.tuesday_select)).perform(click());
        onView(withId(R.id.saturday_select)).perform(click());

        //click add
        onView(withText("ADD")).perform(click());



    }

    @Test
    public void testDeleteHabit() {
        //onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));

    }

}
