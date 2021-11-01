package com.team11.ditto;

/*
Role: Initialize a Dialog for the user to input a title, reason, dates for a new Habit.
Send input back to MyHabitActivity and Firestore Database collection "Habit"
Goals: Needs work on the visual aspect (to be done in xml)

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
import java.util.List;

/**
 * This is a class that initializes a Dialog for the user to input a title, reason, dates for a new Habit.
 * Sends input back to MyHabitActivity and Firestore Database collection "Habit"
 * @author Kelly Shih, Aidan Horemans
 */
public class AddHabitFragment extends DialogFragment implements CompoundButton.OnCheckedChangeListener{
    private EditText hTitle;
    private EditText hReason;
    private ArrayList<Integer> dates;
    private OnFragmentInteractionListener listener;


    public interface OnFragmentInteractionListener {
        void onOkPressed(Habit newHabit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnFragmentInteractionListener) context;
        dates = new ArrayList<>();
    }

    /**
     * Create the dialog with the fields for title, reason, dates, and go to OnOkPressed method when user clicks "Add"
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_habit_fragment,null);
        hTitle = view.findViewById(R.id.title_editText);
        hReason = view.findViewById(R.id.reason_editText);

        //hDate = view.findViewById(R.id.date_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Habit")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        switch(compoundButton.getId()){
            case R.id.monday_select:
                if(checked)
                    dates.add(1);
                else
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
