package com.team11.ditto;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.text.DateFormat;
import java.util.Calendar;

public class AddHabitFragment extends DialogFragment {
    private EditText hTitle;
    private EditText hReason;
    private EditText hDate;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener {
        void onOkPressed(Habit newHabit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_habit_fragment,null);
        hTitle = view.findViewById(R.id.title_editText);
        hReason = view.findViewById(R.id.reason_editText);
        hDate = view.findViewById(R.id.date_editText);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Habit")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = hTitle.getText().toString();
                        String reason = hReason.getText().toString();
                        String date = hDate.getText().toString();


                        listener.onOkPressed(new Habit(title, reason, date));

                    }
                }).create();

    }
}
