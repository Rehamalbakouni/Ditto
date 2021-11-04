package com.team11.ditto;
/**
 * Test class for MyHabitActivity. All the UI tests are written here. Espresso test framework is
 used
 TODO: Future tests:
 * public void testEditHabitTitle() //tests the edit function which will be implemented
 * public void testViewPersists() //tests that the view updates the updated data
 * check for repeated Habit titles!!!
 * public void habitEventDeleted() //when you delete a habit activity, it should also delete the associated habit events
 */
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HabitsTest {

    @Rule
    public ActivityScenarioRule<MyHabitActivity> activityRule = new ActivityScenarioRule<>(MyHabitActivity.class);



    /**
     * Add to all fields of the add fragment
     */
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


    /**
     * Test the swipe action to delete
     */
    @Test
    public void testDeleteHabit() {
        //first add a habit then delete
        String title = "Read a book";
        String reason = "Become literate";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.title_editText)).perform(typeText(title));
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        onView(withId(R.id.tuesday_select)).perform(click());
        onView(withId(R.id.saturday_select)).perform(click());

        //click add
        onView(withText("ADD")).perform(click());

        //the delete habit
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));

    }

    /**
     * just add the title test
     */
    @Test
    public void testAddTitle() {
        String title = "Eating";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.title_editText)).perform(typeText(title));

        //click add
        onView(withText("ADD")).perform(click());

    }

    /**
     * TODO: Testing unable to add without a habit title
     */
    @Test
    public void testNoTitleAdd() {
        String reason = "To get abs";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        onView(withId(R.id.tuesday_select)).perform(click());
        onView(withId(R.id.saturday_select)).perform(click());

        //click add
        onView(withText("ADD")).perform(click());

    }

    /**
     * test the view habit action -> click on a habit and go to a new activity
     */
    @Test
    public void testViewHabit() {
        //first add a habit then view it
        String title = "Massage";
        String reason = "Stress relief";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.title_editText)).perform(typeText(title));
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        onView(withId(R.id.tuesday_select)).perform(click());
        onView(withId(R.id.saturday_select)).perform(click());

        //click add
        onView(withText("ADD")).perform(click());

        //click and view activity for habit
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    /**
     * test if editing the details of a habit crashes
     */
    @Test
    public void testEditHabitDetails() {
        //make sure there is a habit in the listview to start
        //first add a habit then view it
        String title = "Eat cake";
        String reason = "Stress relief";

        //open the add habit fragment
        onView(withId(R.id.add_habit)).perform(click());

        //type in title and reason
        onView(withId(R.id.title_editText)).perform(typeText(title));
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        onView(withId(R.id.tuesday_select)).perform(click());
        onView(withId(R.id.saturday_select)).perform(click());

        //click add
        onView(withText("ADD")).perform(click());

        //click on a habit
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //click on the edit button
        onView(withId(R.id.edit_habit)).perform(click());

        //edit the reason and dates
        String newreason = "Birthday";
        onView(withId(R.id.reason_editText)).perform(typeText(reason));
        //click on one of the same date and unclick one, add new cases
        //tuesday should still be clicked
        onView(withId(R.id.saturday_select)).perform(click()); //basically unclick
        onView(withId(R.id.wednesday_select)).perform(click()); //add a new one



    }


    /*
    Future tests:
    public void testEditHabitTitle() //tests the edit function which will be implemented
    public void testViewPersists() //tests that the view updates the updated data
     */



}
