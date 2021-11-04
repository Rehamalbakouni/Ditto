package com.team11.ditto;

import static com.team11.ditto.SwitchTabs.PROFILE_TAB;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test class for following activity
 */
public class FollowTests {
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setup() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }
    /**
     *  Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }
    /**
     * Check if the view is switched
     */
    @Test
    public void viewSwitched(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

        // Get the tab view, get "User tab location"
        MainActivity activity = (MainActivity) solo.getCurrentActivity();

        TabLayout tabss = (TabLayout) solo.getView(R.id.tabs);

        // WEIRD Robotium, open DueTodayactivity at index 0
        View profile = tabss.getChildAt(1);
        solo.clickOnView(profile);

        
//        solo.waitForActivity(UserProfileActivity.class,2000);
//        solo.assertCurrentActivity("Wrong Activity",DueTodayActivity.class);
//
//        ViewGroup tabs1 = (ViewGroup) solo.getView(R.id.tabs);
//        //View profile1 = (View) solo.getView(android.R.id.tabs);
//        solo.clickOnView(tabs1);

//        View profile2 = tabs.getChildAt(1);
//        solo.clickOnView(profile2);


    }
}
