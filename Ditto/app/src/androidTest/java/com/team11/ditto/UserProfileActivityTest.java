/** Copyright [2021] [Reham Albakouni, Matt Asgari Motlagh, Aidan Horemans, Courtenay Laing-Kobe, Vivek Malhotra, Kelly Shih]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.team11.ditto;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
        onView(withId(R.id.follow_request_sent)).perform(click());

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