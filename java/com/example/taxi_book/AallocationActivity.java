package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AallocationActivity extends AppCompatActivity {
    private EditText Vehicle_Name;
    private EditText Vehicle_Number;
    private EditText D_Name;
    private EditText D_Number;
    private Spinner Spinner1;
    private Button Save;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aallocation);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        Vehicle_Name = (EditText) findViewById(R.id.name);
        Vehicle_Number = (EditText) findViewById(R.id.Number);
        D_Name = (EditText) findViewById(R.id.DriverName);
        D_Number = (EditText) findViewById(R.id.number);
        Save = (Button) findViewById(R.id.save);
        Spinner1 = (Spinner) findViewById(R.id.vehiCondition);

        Spinner spinner1 = findViewById(R.id.vehiCondition);
        String[] items3 = new String[]{"Vehicle Condition", "A/C", "Non_A/C"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        spinner1.setAdapter(adapter3);

        progressDialog = new ProgressDialog(this);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cab_Number=Vehicle_Number.getText().toString().trim();
                String cab_Name=Vehicle_Name.getText().toString().trim();
                String Driver_Name=D_Name.getText().toString().trim();
                String Driver_Number=D_Number.getText().toString().trim();
                String cab_Condition=Spinner1.getSelectedItem().toString().trim();

                String Cab_Number = databaseReference.push().getKey();

                if (TextUtils.isEmpty(cab_Number)) {

                    Toast.makeText( AallocationActivity.this, "Please Enter Vehicle id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cab_Name)) {

                    Toast.makeText( AallocationActivity.this, "Please Enter Vehicle Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Driver_Name)) {

                    Toast.makeText( AallocationActivity.this, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Driver_Number)) {

                    Toast.makeText( AallocationActivity.this, "Please Enter Owner Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.equals(cab_Condition,"Vehicle Condition")) {

                    Toast.makeText( AallocationActivity.this, "Please Select Vehicle Condition", Toast.LENGTH_SHORT).show();
                    return;
                }

                AllocationDetails AllocationDetails = new AllocationDetails( cab_Number , cab_Name,  Driver_Name,Driver_Number ,cab_Condition);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child("AllocationDetails").child(cab_Number).setValue(AllocationDetails);
                progressDialog.setMessage("Adding Details Please Wait...");
                progressDialog.show();

                Intent intent=new Intent(getApplicationContext(),  AallocationActivity.class);

                startActivity(intent);
                progressDialog.dismiss();
            }
        });
    }
}