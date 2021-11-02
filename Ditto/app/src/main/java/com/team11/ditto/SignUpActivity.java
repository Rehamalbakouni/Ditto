package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.PasswordAuthentication;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText user_name;
    private EditText age;
    private EditText password;
    private EditText confirm_password;
    private TextView register;
    private TextView already_user_btn;
    FirebaseFirestore db;
    private User user;
    final String TAG = "Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user_name = findViewById(R.id.userName);
        age = findViewById(R.id.age);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        confirm_password = findViewById(R.id.confirmPassword);
        already_user_btn = findViewById(R.id.alreadyUser);
        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("User");


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entered_username = user_name.getText().toString().trim();
                String string_age = age.getText().toString().trim();
                Integer entered_age = Integer.parseInt(string_age);
                String entered_password = password.getText().toString().trim();
                String confirmed_password = confirm_password.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(entered_username)) {
                    user_name.setError("UserName is required.");
                } else if (TextUtils.isEmpty(string_age)) {
                    user_name.setError("Age is required.");
                } else if (TextUtils.isEmpty(entered_password)) {
                    user_name.setError("Password is required.");
                } else if (TextUtils.isEmpty(entered_password)) {
                    user_name.setError("Confirm Password is required.");
                } else if (password.length() < 8) {
                    password.setError("Password must be at least 8 characters.");
                } else if (!entered_password.equals(confirmed_password)) {
                    confirm_password.setError("Password does not match.");
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    user = new User(entered_username, entered_password, entered_age);
                    //input is valid -> create the user
                    data.put("username", entered_username);
                    data.put("password", entered_password);
                    data.put("age", string_age);

                    DocumentReference documentReference = db.collection("User").document(entered_username);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {


                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                assert document != null;
                                if (document.exists()) {
                                    //Username already exists
                                    Log.d(TAG, "This username is already exists.");
                                } else {
                                    collectionReference
                                            .document(entered_username)
                                            .set(data)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d(TAG, "User has been added successfully!");
                                                    //startActivity(home)
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // These are a method which gets executed if there’s any problem
                                                    Log.d(TAG, "Data could not be added!" + e.toString());
                                                }
                                            });
                                }
                            }
                        }
                    });
                }
                // switch to sign in activity if the user already has ana account
                already_user_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    }
                });

            }
        });
    }
}