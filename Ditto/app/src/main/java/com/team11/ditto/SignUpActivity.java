package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    EditText user_name;
    EditText age;
    EditText password;
    EditText confirm_password;
    TextView register;
    TextView already_user_btn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        user_name = findViewById(R.id.userName);
        age = findViewById(R.id.Age);
        password = findViewById(R.id.Password);
        register = findViewById(R.id.Register);
        confirm_password = findViewById(R.id.confirmPassword);
        already_user_btn = findViewById(R.id.alreadyUser);
        db = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entered_username = user_name.getText().toString();
                String entered_age = age.getText().toString();
                String entered_password = password.getText().toString();
                String confirmed_password = confirm_password.getText().toString();

                // Validate input
                if(TextUtils.isEmpty(entered_username)){
                    user_name.setError("UserName is required.");
                    return;
                }
                if(TextUtils.isEmpty(entered_age)){
                    user_name.setError("Age is required.");
                    return;
                }
                if(TextUtils.isEmpty(entered_password)){
                    user_name.setError("Password is required.");
                    return;
                }
                if(TextUtils.isEmpty(entered_password)){
                    user_name.setError("Confirm Password is required.");
                    return;
                }
                if (password.length() < 8){
                    password.setError("Password must be at least 8 characters.");
                    return;
                }

                if(!entered_password.equals(confirmed_password)){
                    confirm_password.setError("Password does not match.");
                }



            }
        });
        // switch to sign in activity if the user already has ana account
        already_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

    }
}
