package com.team11.ditto;

/*
Role: Initialize a Dialog for the user to input a title, reason, dates for a new Habit.
Send input back to MyHabitActivity and Firestore Database collection "Habit"
TODO Needs work on the visual aspect (to be done in xml)
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is a class that initializes a Dialog for the user to input a title, reason, dates for a new Habit.
 * Sends input back to MyHabitActivity and Firestore Database collection "Habit"
 * TODO: Needs work on the visual aspect (to be done in xml)
 * @author Kelly Shih, Aidan Horemans
 */
public class AddHabitFragment extends DialogFragment{
    //Declare views & interaction listener
    private EditText hTitle;
    private EditText hReason;
    private ArrayList<Integer> dates;
    private OnFragmentInteractionListener listener;


    //Declare interface
    public interface OnFragmentInteractionListener {
        void onOkPressed(Habit newHabit);
    }

    /**
     * Tells Android what to do when the Fragment is attached to the Activity
     * @param context the Activity context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnFragmentInteractionListener) context;
    }

    /**
     * Create the dialog with the fields for title, reason, dates, and go to OnOkPressed method when user clicks "Add"
     * @param savedInstanceState current App state
     * @return Dialog for user creation of a new habit
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_habit_fragment,null);
        hTitle = view.findViewById(R.id.title_editText);
        hReason = view.findViewById(R.id.reason_editText);
        dates = new ArrayList<>();

        CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;
        //initializing checkboxes... i think this is the best way to do it... dear god
        chk1 = view.findViewById(R.id.monday_select); chk2 = view.findViewById(R.id.tuesday_select);
        chk3 = view.findViewById(R.id.wednesday_select); chk4 = view.findViewById(R.id.thursday_select);
        chk5 = view.findViewById(R.id.friday_select); chk6 = view.findViewById(R.id.saturday_select);
        chk7 = view.findViewById(R.id.sunday_select);
        chk1.setOnCheckedChangeListener(this::onCheckedChanged);
        chk2.setOnCheckedChangeListener(this::onCheckedChanged);
        chk3.setOnCheckedChangeListener(this::onCheckedChanged);
        chk4.setOnCheckedChangeListener(this::onCheckedChanged);
        chk5.setOnCheckedChangeListener(this::onCheckedChanged);
        chk6.setOnCheckedChangeListener(this::onCheckedChanged);
        chk7.setOnCheckedChangeListener(this::onCheckedChanged);

        //hDate = view.findViewById(R.id.date_editText);

        //Builds the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Habit")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    /**
                     * On clicking the "add" button, create a new Habit object with the new data inputted by the user
                     * @param dialogInterface Android default
                     * @param i Android default
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = hTitle.getText().toString();
                        String reason = hReason.getText().toString();
                        Collections.sort(dates);

                        listener.onOkPressed(new Habit(title, reason, dates));
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }


    /**
     * A method to add the weekday (1,2,3,4,5,6,7) to the dates list for the dates the user wants to do the activity
     * @param compoundButton checkbox
     * @param checked whether the box is checked
     *  TODO Android Studio suggests not using resource Ids in switch statements
     */
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        switch(compoundButton.getId()){
            case R.id.monday_select:
                if(checked) {
                    dates.add(1);
                }else
                    dates.remove(Integer.valueOf(1));
                break;
            case R.id.tuesday_select:
                if(checked)
                    dates.add(2);
                else
                    dates.remove(Integer.valueOf(2));
                break;
            case R.id.wednesday_select:
                if(checked)
                    dates.add(3);
                else
                    dates.remove(Integer.valueOf(3));
                break;
            case R.id.thursday_select:
                if(checked)
                    dates.add(4);
                else
                    dates.remove(Integer.valueOf(4));
                break;
            case R.id.friday_select:
                if(checked)
                    dates.add(5);
                else
                    dates.remove(Integer.valueOf(5));
                break;
            case R.id.saturday_select:
                if(checked)
                    dates.add(6);
                else
                    dates.remove(Integer.valueOf(6));
                break;
            case R.id.sunday_select:
                if(checked)
                    dates.add(7);
                else
                    dates.remove(Integer.valueOf(7));
                break;
        }
    }

}
