package com.team11.ditto;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity implements Firebase{
    private EditText user_name;
    private EditText age;
    private EditText password;
    private EditText confirm_password;
    private TextView register;
    private TextView already_user_btn;
    FirebaseFirestore db;
    private User user;
    final String TAG = "Sample";

    String entered_username;
    String string_age;
    int entered_age;
    String entered_password;
    String confirmed_password;

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

        register.setOnClickListener(view -> {
            entered_username = user_name.getText().toString().trim();
            string_age = age.getText().toString().trim();
            entered_age = Integer.parseInt(string_age);
            entered_password = password.getText().toString().trim();
            confirmed_password = confirm_password.getText().toString().trim();

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
                user = new User(entered_username, entered_password, entered_age);
                //input is valid -> create the user
                pushUserData(db, user);
            }
            // switch to sign in activity if the user already has ana account
            //already_user_btn.setOnClickListener(view1 -> startActivity(new Intent(SignUpActivity.this, SignInActivity.class)));
        });
    }

    @Override
    public void pushToDB(FirebaseFirestore database, String key) {
        final CollectionReference collectionReference = db.collection(USER_KEY);
        DocumentReference documentReference = db.collection(USER_KEY).document(entered_username);
        documentReference.get().addOnCompleteListener(task -> {
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
                            .addOnSuccessListener(unused -> {
                                Log.d(TAG, "User has been added successfully!");
                                //startActivity(home)
                            })
                            .addOnFailureListener(e -> {
                                // These are a method which gets executed if there’s any problem
                                Log.d(TAG, "Data could not be added!" + e.toString());
                            });
                }
            }
        });
    }
}