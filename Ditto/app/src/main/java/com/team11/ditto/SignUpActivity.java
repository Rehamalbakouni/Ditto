package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity implements Firebase{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView alreadyUserBtn = findViewById(R.id.alreadyUser);
        alreadyUserBtn.setOnClickListener(view ->
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class)));

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        EditText confirmPW = findViewById(R.id.confirmPassword);
        EditText password = findViewById(R.id.password);
        EditText userName = findViewById(R.id.userName);
        EditText age = findViewById(R.id.age);
        Button registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(view -> {
            if (confirmPW.getText().toString().equals(password.getText().toString())) {
                User newUser = new User(userName.getText().toString(),
                        password.getText().toString(),
                        Integer.parseInt(age.getText().toString()));
                database.collection("User").add(newUser).addOnSuccessListener(
                        documentReference -> {
                            String userID = documentReference.getId();
                            newUser.setID(userID);
                        });

            }
        }

        );



    }
}
