package com.team11.ditto.interfaces;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;
import com.team11.ditto.DueTodayActivity;
import com.team11.ditto.MainActivity;
import com.team11.ditto.MyHabitActivity;
import com.team11.ditto.UserProfileActivity;

public interface SwitchTabs {

    int HOME_TAB = 0;
    int MY_HABITS_TAB = 1;
    int DUE_TODAY_TAB = 2;
    int PROFILE_TAB = 3;

    default void switchTabs(Context context, TabLayout tabLayout, int currentTabPos){

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabSelect) {
                // position 0 is for home
                if ((tabSelect.getPosition() == HOME_TAB) && (currentTabPos != HOME_TAB)){
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }

                // position 1 is for MyHabits
                else if ((tabSelect.getPosition() == MY_HABITS_TAB) && (currentTabPos != MY_HABITS_TAB)){
                    Intent intent = new Intent(context, MyHabitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }

                // position 2 is for Due Today
                else if ((tabSelect.getPosition() == DUE_TODAY_TAB) && (currentTabPos != DUE_TODAY_TAB)){
                    Intent intent = new Intent(context, DueTodayActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }

                // position 3 is for Profile
                else if ((tabSelect.getPosition() == PROFILE_TAB) && (currentTabPos != PROFILE_TAB)){
                    Intent intent = new Intent(context, UserProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    default void currentTab(TabLayout tabLayout, int currentTabPos){
        TabLayout.Tab tab = tabLayout.getTabAt(currentTabPos);
        if (tab != null) {
            tab.select();
        }

    }
}