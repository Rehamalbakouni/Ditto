package com.team11.ditto;
/*
The Class for MyHabit Activity Screen
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team11.ditto.login.SignUpActivity;
import com.team11.ditto.login.SignInActivity;

/**
 * Role: Class for Welcome Activity, onboard a user
 * @author: Matthew Asgari
 */
public class WelcomeActivity extends Activity {

    private Button getStartedButton;
    private Button loginButton;
    private Activity activity = this;

    /**
     * Create the Activity instance for the "Welcome" screen, control flow of actions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Load buttons
        getStartedButton = findViewById(R.id.sign_up_button);
        loginButton = findViewById(R.id.sign_in_button);

        // Set on click listener for "Get Started!" button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Set on click listener for "Sign In" button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}