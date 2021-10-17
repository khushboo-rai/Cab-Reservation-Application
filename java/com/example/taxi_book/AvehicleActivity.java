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

public class AvehicleActivity extends AppCompatActivity {
    private EditText Txt_id;
    private EditText Txt_Name;
    private EditText Txt_Number;
    private EditText Txt_OwnerName;
    private Spinner Spinner1;
    private Button addVehicle;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avehicle);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        Txt_id = (EditText) findViewById(R.id.id);
        Txt_Name = (EditText) findViewById(R.id.name);
        Txt_Number = (EditText) findViewById(R.id.Number);
        Txt_OwnerName = (EditText) findViewById(R.id.ownerName);
        addVehicle = (Button) findViewById(R.id.add);
        Spinner1 = (Spinner) findViewById(R.id.vehiCondition);
        //From

        //Condition
        Spinner spinner1 = findViewById(R.id.vehiCondition);
        String[] items3 = new String[]{"Vehicle Condition", "A/C", "Non_A/C"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        spinner1.setAdapter(adapter3);

        progressDialog = new ProgressDialog(this);

        addVehicle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String cab_id = Txt_id.getText().toString().trim();
                String cab_Name = Txt_Name.getText().toString().trim();
                String cab_Number= Txt_Number.getText().toString().trim();
                String owner_Name  = Txt_OwnerName.getText().toString().trim();
                String cab_Condition =Spinner1.getSelectedItem().toString().trim();

                String CabId = databaseReference.push().getKey();

                if (TextUtils.isEmpty(cab_id)) {
                    //email is empty
                    Toast.makeText(AvehicleActivity.this, "Please Enter Vehicle id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cab_Name)) {
                    //password is empty
                    Toast.makeText(AvehicleActivity.this, "Please Enter Vehicle Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cab_Number)) {
                    //password is empty
                    Toast.makeText(AvehicleActivity.this, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(owner_Name)) {
                    //password is empty
                    Toast.makeText(AvehicleActivity.this, "Please Enter Owner Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.equals(cab_Condition,"Vehicle Condition")) {
                    //password is empty
                    Toast.makeText(AvehicleActivity.this, "Please Select Vehicle Condition", Toast.LENGTH_SHORT).show();
                    return;
                }
                Avehicledetails vehicleDetails = new Avehicledetails(cab_id, cab_Name, cab_Number , owner_Name,cab_Condition);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child("VehicleDetails").child(cab_id).setValue(vehicleDetails);
                progressDialog.setMessage("Adding Vehicle Please Wait...");
                progressDialog.show();

                Intent intent=new Intent(getApplicationContext(), AvehicleActivity.class);

                startActivity(intent);
                progressDialog.dismiss();

            }
        });
    }

}

