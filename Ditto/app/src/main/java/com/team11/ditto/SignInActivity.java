package com.team11.ditto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {
    private EditText usernameLogin;
    private EditText passwordLogin;
    private Button signinBtn;
    FirebaseFirestore db;
    final String TAG = "Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        usernameLogin = findViewById(R.id.userName2);
        passwordLogin = findViewById(R.id.Password2);
        signinBtn = findViewById(R.id.signIn);
        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("User");

        signinBtn.setOnClickListener(view -> {
            String entered_username = usernameLogin.getText().toString();
            String password = passwordLogin.getText().toString();
            DocumentReference documentReference = db.collection("User").document(entered_username);
            documentReference.get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    Log.d(TAG, "Correct information.");
                    //StartActivity(home)
                }
                else {
                    Log.d(TAG, "Not found");
                }

            });
        });

        TextView createAccountBtn = findViewById(R.id.createAccount);
        createAccountBtn.setOnClickListener(view -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));

        String username = usernameLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        //search db for user

        //check user password

        //if user exists and password is correct, set the user as ActiveUser
        /*createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
        */

    }
}
