package com.team11.ditto;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test class for MainActivity. All the UI tests are written here. Espresso test framework is
 used
 TODO: FutureTests:
 @Before
 public void clearHabitEvents()

 public void testAddPhoto()
 public void testAddGeolocation()
 public void testCantEditTitle() //make sure we cant edit a habit event title
 public void testEditHabitEvent() //make sure we can edit details like reason, photo, location, and dates for a habit event
 */

@RunWith(AndroidJUnit4.class)
public class HabitEventFeedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * test the add habit event fragment for choosing habit and commenting
     * TODO: create tests for adding photos and location
     */
    @Test
    public void testAddHabitEventAll() {
        String habit = "Running";
        String comment = "I am sweaty";

        //open the add habit fragment
        onView(withId(R.id.add_habit_event)).perform(click());

        //click choose a habit, create comment, add
        onView(withId(R.id.event_spinner)).perform(click());

        onView(withText(habit)).inRoot(isPlatformPopup()).perform(click());

        //check if what you chose is correct
        onView(withId(R.id.event_spinner))
                .check(matches(withSpinnerText(containsString("Running"))));


        onView(withId(R.id.comment_editText)).perform(typeText(comment));

        //click the add button
        onView(withText("ADD")).perform(click());

    }

}
