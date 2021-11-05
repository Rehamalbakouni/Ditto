package com.team11.ditto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private TextView registerButton;
    private User user;
    final String TAG = "Sample";

    Context context = this;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        nameField = findViewById(R.id.signup_name_field);
        emailField = findViewById(R.id.signup_email_field);
        passwordField = findViewById(R.id.signup_password_field);
        registerButton = findViewById(R.id.register_button);
        confirmPasswordField = findViewById(R.id.signup_password_confirm_field);
        db = FirebaseFirestore.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                String passwordConfirm = confirmPasswordField.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(name)) {
                    nameField.setError("Name is required.");
                } else if (TextUtils.isEmpty(email)) {
                    emailField.setError("email is required.");
                } else if (TextUtils.isEmpty(password)) {
                    passwordField.setError("Password is required.");
                } else if (TextUtils.isEmpty(passwordConfirm)) {
                    confirmPasswordField.setError("Confirm Password is required.");
                } else if (password.length() < 8) {
                    passwordField.setError("Password must be at least 8 characters.");
                } else if (!password.equals(passwordConfirm)) {
                    confirmPasswordField.setError("Password does not match.");
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    user = new User(name, email);
                    //input is valid -> create the user
                    data.put("name", name);
                    data.put("email", email);

                    registerUser(email, password, data);
                }
            }
        });
    }


    // Attempt to register a user on Firebase
    private void registerUser(String email, String password, HashMap<String, String> userData) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            storeUserData(user.getUid(), userData);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Store user data on Firestore
    private void storeUserData(String uid, HashMap<String, String> userData) {
        CollectionReference collectionReference = db.collection("User");
        DocumentReference documentReference = collectionReference.document(uid);

        documentReference
            .set(userData)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "User data added successfully!");

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // These are a method which gets executed if there’s any problem
                    Log.w(TAG, "Data could not be added!" + e.toString());
                }
            });
    }
}