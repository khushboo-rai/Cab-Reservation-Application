package com.example.taxi_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout  txt_fullName;
    private TextInputLayout txt_email;
    private TextInputLayout  txt_Password;
    private TextInputLayout  txt_phone;
    RadioButton radioGenderMale, radioGenderFemale;
    private Button eRegister;
    String gender = "";
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_fullName = (TextInputLayout) findViewById(R.id.name);
        txt_email = (TextInputLayout) findViewById(R.id.mail);
        txt_Password = (TextInputLayout) findViewById(R.id.password);
        txt_phone= (TextInputLayout) findViewById(R.id.number);

        radioGenderMale = (RadioButton) findViewById(R.id.male);
        radioGenderFemale = (RadioButton) findViewById(R.id.female);

        eRegister = (Button) findViewById(R.id.register);

        databaseReference = FirebaseDatabase.getInstance().getReference("rider");
        firebaseAuth=FirebaseAuth.getInstance();



        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fullName = txt_fullName.getEditText().getText().toString();
                String email = txt_email.getEditText().getText().toString();
                String phone =  txt_phone.getEditText().getText().toString();
                String password = txt_Password.getEditText().getText().toString();

                if (radioGenderMale.isChecked()) {

                    gender="Male";
                }
                if (radioGenderFemale.isChecked()){
                    gender="Female";
                }

                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(RegisterActivity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this, "please enter Phone number", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            rider information = new rider(
                                    fullName,
                                    email,
                                    phone,
                                    gender
                            );


                            FirebaseDatabase.getInstance().getReference("rider")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),RiderDashBoardActivity.class));
                                }
                            });
                        } else {

                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();


                        }
}


            });

        }
    });
}

}