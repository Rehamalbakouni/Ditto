package com.team11.ditto;
/*
Add Habit Event Fragment Class
Goals: To create restraints on user i.e. warn user to pick a Habit before pressing Add
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddHabitEventFragment extends DialogFragment {
    private EditText hComment;
    private OnFragmentInteractionListener listener;


    public interface OnFragmentInteractionListener {
        void onOkPressed(Habit newHabitEvent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddHabitEventFragment.OnFragmentInteractionListener) context;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_habit_fragment, null);
        hComment = view.findViewById(R.id.comment_editText);

        Spinner spinner = (Spinner) view.findViewById(R.id.event_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.habits_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Habit Event")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String comment = hComment.getText().toString();
                        //get value from spinner, if "Habit" is chosen, they've chosen no unit
                        String hHabit = spinner.getSelectedItem().toString();
                        if (hHabit.equals("Habit")) {
                            hHabit = "";
                        }

                        //just an example date, take out later...
                        String date = "100";

                        //right now how are we adding a new Habit???
                        listener.onOkPressed(new Habit(hHabit, comment, date));

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();


    }
    }
