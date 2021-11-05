package com.team11.ditto;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.os.SystemClock;
import android.widget.EditText;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TODO: This test will be complete when the user is active
 */
@RunWith(AndroidJUnit4.class)
public class UserProfileActivityTest {

    @Rule
    public ActivityScenarioRule<UserProfileActivity> activityRule = new ActivityScenarioRule<>(UserProfileActivity.class);

    /**
     *  Check if the search functionality works
     */
    @Test
    public void searchUser() {

        String user = "Vivek";

        // click on search for users
        onView(withId(R.id.search_users)).perform(click());

        // search for vivek
        onView(withId(R.id.search_user)).perform(click());


        onView(withId(R.id.search_user)).perform(typeText(user));
        SystemClock.sleep(3000);
        // check if vivek appears on screen
        onView(withId(R.id.search_list_custom)).check(matches(hasDescendant(withText("Vivek"))));
        SystemClock.sleep(3000);
    }

    /**
     * Check if pending follow request functionality works
     */
    @Test
    public void pendingRequest() {
        String user = "Bruce Wayne";

        // click on PENDING FOLLOW REQUEST BUTTON
        onView(withId(R.id.pending_fr)).perform(click());

        // check if Bruce Wayne appears on screen
        onView(withId(R.id.following_request_custom)).check(matches(hasDescendant(withText(user))));
        SystemClock.sleep(3000);
    }

    /**
     *  Check if followers work
     */
    @Test
    public void checkFollowers() {
        String user = "Ezio Auditore da Firenze";
        // click on followers
        onView(withId(R.id.followers)).perform(click());
        SystemClock.sleep(3000);
        // check if Ezio Auditore da Firenze appears on screen
        onView(withId(R.id.follower_list_custom)).check(matches(hasDescendant(withText(user))));
        SystemClock.sleep(3000);
    }

    /**
     *  Check if following functionality works
     */
    @Test
    public void checkFollowing() {
        String user = "Aryan";

        // click on following
        onView(withId(R.id.following)).perform(click());
        SystemClock.sleep(3000);
        // check if Aryan appears on screen
        onView(withId(R.id.following_list_custom)).check(matches(hasDescendant(withText(user))));
        SystemClock.sleep(3000);
    }


    /**
     *  Check if we can see the public habit and indicator of those who we are following
     */
    @Test
    public void checkFollowingHabits(){
        String user = "Aryan";

        // click on following
        onView(withId(R.id.following)).perform(click());
        SystemClock.sleep(3000);
        // check if Aryan appears on screen
        onView(withId(R.id.following_list_custom)).check(matches(hasDescendant(withText(user))));
        SystemClock.sleep(3000);

        // click on Aryan
        onView(withText(user)).perform(click());

    }
}