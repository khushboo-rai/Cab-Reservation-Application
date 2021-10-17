package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdriversActivity extends AppCompatActivity {

    private EditText Txt_Name;
    private EditText Txt_Email;
    private EditText Txt_Number;
    private EditText Txt_Address;
    private Button Save;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adrivers);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        Txt_Email = (EditText) findViewById(R.id.id);
        Txt_Name = (EditText) findViewById(R.id.name);
        Txt_Number = (EditText) findViewById(R.id.Number);
        Txt_Address = (EditText) findViewById(R.id.address);
        Save = (Button) findViewById(R.id.save);

        progressDialog = new ProgressDialog(this);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = Txt_Email.getText().toString().trim();
                String Name = Txt_Name.getText().toString().trim();
                String Phone_Number = Txt_Number.getText().toString().trim();
                String Address = Txt_Address.getText().toString().trim();

                String phone_Number = databaseReference.push().getKey();

                if (TextUtils.isEmpty(Phone_Number)) {

                    Toast.makeText( AdriversActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Name)) {

                    Toast.makeText( AdriversActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {

                    Toast.makeText( AdriversActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Address)) {

                    Toast.makeText( AdriversActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                DriverDetails DriverDetails = new DriverDetails(Email, Name, Phone_Number, Address);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child("DriversDetails").child(phone_Number).setValue(DriverDetails);
                progressDialog.setMessage("Adding Details Please Wait...");
                progressDialog.show();

                Intent intent=new Intent(getApplicationContext(), AdriversActivity.class);

                startActivity(intent);
                progressDialog.dismiss();
            }
        });
    }
}