package com.team11.ditto;
/*
Add Habit Event Fragment Class
Goals:
    To create restraints on user i.e. warn user to pick a Habit before pressing Add
    To not make the first choice "Go eat ramen" for the choices of habits
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddHabitEventFragment extends DialogFragment {
    private EditText hComment;
    private Button acc_photo;
    private OnFragmentInteractionListener listener;
    private FirebaseFirestore db;
    private DatabaseReference root;
    final String TAG = "dbs";


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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_event_fragment, null);
        hComment = view.findViewById(R.id.comment_editText);
        acc_photo = view.findViewById(R.id.add_photo);
        db = FirebaseFirestore.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        Spinner spinner = (Spinner) view.findViewById(R.id.event_spinner);
        final List<String> habits = new ArrayList<String>();
        final List<String> habitIDs = new ArrayList<String>();


        //get the documents from Habit
        db.collection("Habit")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                Log.d(TAG, snapshot.getId() + "=>" + snapshot.getData());
                                String habitTitle = snapshot.get("title").toString();
                                String habitID = snapshot.getId().toString();
                                habits.add(habitTitle);
                                habitIDs.add(habitID);
                            }
                            ArrayAdapter<String> habitAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, habits);
                            habitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(habitAdapter);
                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        final String[] hHabit = new String[1];
        final String[] IDhabit = new String[1];

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                hHabit[0] = habits.get(position).toString();
                IDhabit[0] = habitIDs.get(position).toString();
                Log.d(TAG, "habit Id => "+IDhabit[0]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        //Get camera permission for photo
        acc_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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



                        //If user doesn't choose a Habit...
                        /*
                        if (hHabit.equals("Habit")) {
                            hHabit = "";
                        }

                         */

                        //set photo and location
                        String photo = "";
                        String location = "";

                        //right now how are we adding a new Habit???
                        listener.onOkPressed(new HabitEvent(IDhabit[0], comment, photo, location));

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();


    }
    }
