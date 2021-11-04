package com.team11.ditto;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Role: Initialize a Dialog for the user to edit an EXISTING Habit Event
 * editable -> reason, dates
 *  Send input back to ViewHabitActivity and Firestore Database collection "Habit"
 *  TODO: get the photo and location to be editable
 *  @author Kelly Shih, Aidan Horemans
 */
public class EditHabitFragment extends DialogFragment {
    private TextView hTitle;
    private EditText hReason;
    private ArrayList<Integer> dates;
    private Bundle bundle;
    private Habit selectedHabit;
    private EditHabitFragment.OnFragmentInteractionListener listener;
    private CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;

    public interface OnFragmentInteractionListener {
        void onOkPressed(Habit habit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EditHabitFragment.OnFragmentInteractionListener) context;
    }

    /**
     * Create the dialog with the edit fields for reason, dates, and go to OnOkPressed method when user clicks "Add"
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_habit,null);
        hTitle = view.findViewById(R.id.title_textView);
        hReason = view.findViewById(R.id.reason_editText);

        bundle = getArguments();

        selectedHabit = (Habit) bundle.getSerializable("HABIT");

        hTitle.setText(selectedHabit.getTitle());

        hReason.setText(selectedHabit.getReason());

        dates = selectedHabit.getDate();

        //initializing checkboxes... i think this is the best way to do it... dear god
        chk1 = view.findViewById(R.id.monday_select); chk2 = view.findViewById(R.id.tuesday_select);
        chk3 = view.findViewById(R.id.wednesday_select); chk4 = view.findViewById(R.id.thursday_select);
        chk5 = view.findViewById(R.id.friday_select); chk6 = view.findViewById(R.id.saturday_select);
        chk7 = view.findViewById(R.id.sunday_select);
        chk1.setOnCheckedChangeListener(this::onCheckedChanged); chk2.setOnCheckedChangeListener(this::onCheckedChanged);
        chk3.setOnCheckedChangeListener(this::onCheckedChanged); chk4.setOnCheckedChangeListener(this::onCheckedChanged);
        chk5.setOnCheckedChangeListener(this::onCheckedChanged); chk6.setOnCheckedChangeListener(this::onCheckedChanged);
        chk7.setOnCheckedChangeListener(this::onCheckedChanged);

        //Setting the checkboxes depending on pre-selected days
        if(dates.size() > 0){
            updateCheckboxes();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit Habit")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    /**
                     * On clicking the "add" button, edit the pre-existing Habit object with the new data inputted by the user
                     * @param dialogInterface
                     * @param i
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String reason = hReason.getText().toString();
                        Collections.sort(dates);

                        selectedHabit.setReason(reason);
                        selectedHabit.setDate(dates);

                        listener.onOkPressed(selectedHabit);

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }

    /**
     * On checked listener for each checkbox.
     * We ensure that if the box isn't already checked, then check
     * if the box is checked and the user unchecks, remove it from the dates list
     * @param compoundButton
     * @param checked
     */
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        switch(compoundButton.getId()){
            case R.id.monday_select:
                if(checked){
                    if (dates.indexOf(1) <= -1)
                        dates.add(1);}
                else
                    dates.remove(Integer.valueOf(1));
                break;

            case R.id.tuesday_select:
                if(checked) {
                    if (dates.indexOf(2) <= -1)
                        dates.add(2);}
                else
                    dates.remove(Integer.valueOf(2));
                break;

            case R.id.wednesday_select:
                if(checked) {
                    if (dates.indexOf(3) <= -1)
                        dates.add(3);}
                else
                    dates.remove(Integer.valueOf(3));
                break;

            case R.id.thursday_select:
                if(checked) {
                    if (dates.indexOf(4) <= -1)
                        dates.add(4); }
                else
                    dates.remove(Integer.valueOf(4));
                break;

            case R.id.friday_select:
                if(checked) {
                    if (dates.indexOf(5) <= -1)
                        dates.add(5); }
                else
                    dates.remove(Integer.valueOf(5));
                break;

            case R.id.saturday_select:
                if(checked) {
                    if (dates.indexOf(6) <= -1)
                        dates.add(6);}
                else
                    dates.remove(Integer.valueOf(6));
                break;

            case R.id.sunday_select:
                if(checked) {
                    if (dates.indexOf(7) <= -1)
                        dates.add(7);}
                else
                    dates.remove(Integer.valueOf(7));
                break;
        }
    }

    /**
     * set the check boxes as soon as the fragment opens
     */
    private void updateCheckboxes(){
        if(dates.contains(1)){
            chk1.setChecked(true);
        }
        if(dates.contains(2)) {
            chk2.setChecked(true);
        }
        if(dates.contains(3)) {
            chk3.setChecked(true);
        }
        if(dates.contains(4)) {
            chk4.setChecked(true);
        }
        if(dates.contains(5)) {
            chk5.setChecked(true);
        }
        if(dates.contains(6)) {
            chk6.setChecked(true);
        }
        if(dates.contains(7)) {
            chk7.setChecked(true);
        }
    }
}
