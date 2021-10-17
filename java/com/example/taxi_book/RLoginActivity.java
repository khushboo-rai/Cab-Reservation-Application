package com.example.taxi_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RLoginActivity extends AppCompatActivity {
    private TextInputLayout emailTextView, passwordTextView;
    private Button login;
    private Button admin;
    private FirebaseAuth mAuth;
    private Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.mail);
        passwordTextView = findViewById(R.id.password);
        login = findViewById(R.id.go);
        sign_up = findViewById(R.id.user);
        admin= findViewById(R.id.forget);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RLoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RLoginActivity.this,ALoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
    private void loginUserAccount() {
        String email, password;
        email = emailTextView.getEditText().getText().toString();
        password = passwordTextView.getEditText().getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(
                    @NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Login successful!!",
                            Toast.LENGTH_LONG)
                            .show();
                    Intent intent
                            = new Intent(RLoginActivity.this, RiderDashBoardActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Login failed!!",
                            Toast.LENGTH_LONG)
                            .show();
                }


            }
        });
    }
}