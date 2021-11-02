package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView createAccountBtn = findViewById(R.id.createAccount);

        createAccountBtn.setOnClickListener(view -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));

        EditText usernameField = findViewById(R.id.userName2);
        EditText passwordField = findViewById(R.id.password2);

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        //search db for user

        //check user password

        //if user exists and password is correct, set the user as ActiveUser





    }
}
