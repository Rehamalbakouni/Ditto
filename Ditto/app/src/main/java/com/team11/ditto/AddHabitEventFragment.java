package com.team11.ditto;
/*
Role: Initialize a Dialog for the user to choose an EXISTING Habit from the database, comment, photo, location for a new Habit Event.
Send input back to MainActivity and Firestore Database collection "HabitEvent", as well as update "Habit" collection
Goals:
    -To create restraints on user i.e. warn user to pick a Habit before pressing Add
    -To not make the first choice "Go eat ramen" for the choices of habits
    -Get Camera and photo library permission
    -Get location option
    -Make it better visually (xml)
 */
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

/**Initialize a Dialog for the user to choose an EXISTING Habit from the database and add comment, dates for a new Habit Event.
 * Send input back to MainActivity and Firestore Database collection "HabitEvent", as well as update "Habit" collection
 * TODO: allow user to add photo and location
 * @author Kelly Shih, Aidan Horemans
 */
public class AddHabitEventFragment extends DialogFragment implements Firebase{
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

    /**
     * Create the dialog with the fields for habit (spinner), reason, dates and go to OnOkPressed method when user clicks "Add"
     * TODO: photo and location addition
     * @param savedInstanceState
     * @return
     */
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
        getDocumentsHabit(db, habits, habitIDs, spinner, getActivity());

        final String[] hHabit = new String[1];
        final String[] IDhabit = new String[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * to retrieve the habit and habit ID from the selected spinner choice
             * @param parent
             * @param view
             * @param position
             * @param l
             */
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
                    /**
                     * Create a new Habit Event object when the user clicks the add button with inputted data
                     * @param dialogInterface
                     * @param i
                     */
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

                        listener.onOkPressed(new HabitEvent(IDhabit[0], comment, photo, location, hHabit[0]));

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

    }
}
