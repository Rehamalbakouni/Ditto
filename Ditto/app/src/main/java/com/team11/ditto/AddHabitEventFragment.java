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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddHabitEventFragment extends DialogFragment {
    private EditText hComment;
    private OnFragmentInteractionListener listener;
    private FirebaseFirestore db;
    private DatabaseReference root;



    public interface OnFragmentInteractionListener {
        void onOkPressed(HabitEvent newHabitEvent);
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
        db = FirebaseFirestore.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        Spinner spinner = (Spinner) view.findViewById(R.id.event_spinner);



        //Get the Habits from Firestore to populate the dropdown habit selection
        root.child("Habit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> habits = new ArrayList<String>();
                final List<String> habitIDs = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: snapshot.getChildren()) {
                    String habitTitle = areaSnapshot.child("title").getValue(String.class);
                    String habitID = areaSnapshot.getKey();
                    habits.add(habitTitle);
                    habitIDs.add(habitID);
                }


                ArrayAdapter<String> habitAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, habits);
                habitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(habitAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





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

                        //set photo and location
                        String photo = "";
                        String location = "";

                        listener.onOkPressed(new HabitEvent(hHabit, comment, photo, location));

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();


    }
    }
