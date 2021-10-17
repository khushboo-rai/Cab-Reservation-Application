package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ALoginActivity extends AppCompatActivity {
    private TextInputLayout emailTextView, passwordTextView;
    private Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alogin);
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.mail);
        passwordTextView = findViewById(R.id.password);
        login = findViewById(R.id.go);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
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
        if(email.equals("raikhushboo21@gmail.com") && password.equals("123456789"))
        {
            Toast.makeText(getApplicationContext(),
                    "Login successful!!",
                    Toast.LENGTH_LONG)
                    .show();
            Intent intent
                    = new Intent(ALoginActivity .this, ANavbarActivity.class);
            startActivity(intent);
        }
        else {

            Toast.makeText(getApplicationContext(),
                    "Login failed!!",
                    Toast.LENGTH_LONG)
                    .show();
        }

    }
}