package com.team11.ditto;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.os.SystemClock;
import android.view.ViewGroup;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.google.android.material.tabs.TabLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test class for MainActivityTest for the Bottom Tab switching. Espresso test framework is used
 * TODO: check if the current tab we clicked on takes us to the right activity
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {


    /**
     * test to check if the tabs change
     */
    @Test
    public void changTab() {
        // check if the tabs change
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withText("PROFILE")).perform(ViewActions.click()); // click on PROFILE TAB
        Espresso.onView(ViewMatchers.withText("DUE")).perform(ViewActions.click()); // click on PROFILE TAB
        Espresso.onView(ViewMatchers.withText("HABITS")).perform(ViewActions.click()); // click on PROFILE TAB
        Espresso.onView(ViewMatchers.withText("HOME")).perform(ViewActions.click()); // click on PROFILE TAB
        SystemClock.sleep(5000);
        onView(withId(R.id.add_habit_event)).perform(click());
        SystemClock.sleep(5000);
    }

    @After
    public void tearDown() throws Exception {

    }

}