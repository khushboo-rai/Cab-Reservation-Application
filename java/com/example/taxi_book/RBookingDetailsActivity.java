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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RBookingDetailsActivity extends AppCompatActivity {


    EditText txt_Name,txt_Email,Phone;
    Button Confirm_Booking;
    private Spinner Spinner1;
    private ProgressDialog progressDialog;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbooking_details);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        txt_Name = (EditText) findViewById(R.id.name);
        txt_Email = (EditText) findViewById(R.id.id);
        Phone = (EditText) findViewById(R.id.number);
        Confirm_Booking = (Button) findViewById(R.id.confirm_booking);

        progressDialog = new ProgressDialog(this);
        Spinner1 = (Spinner) findViewById(R.id.passenger);

        Spinner spinner1 = findViewById(R.id.passenger);
        String[] items3 = new String[]{"One", "Two", "Three"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        spinner1.setAdapter(adapter3);




        Confirm_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = txt_Name.getText().toString().trim();
                String Email = txt_Email.getText().toString().trim();
                String Phone_Number = Phone.getText().toString().trim();
                String Passenger_Number = Spinner1.getSelectedItem().toString().trim();


                if(TextUtils.isEmpty(Name)){
                    Toast.makeText(RBookingDetailsActivity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(RBookingDetailsActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Phone_Number)){
                    Toast.makeText(RBookingDetailsActivity.this, "please enter number", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.equals(Passenger_Number,"Passenger_Number")) {
                    //password is empty
                    Toast.makeText(RBookingDetailsActivity.this, "Please Select Vehicle Condition", Toast.LENGTH_SHORT).show();
                    return;
                }
                RBookingDetails BookingDetails = new RBookingDetails (Name,
                        Email,
                        Phone_Number,
                        Passenger_Number);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child("BookingDetails").child(Phone_Number).setValue(BookingDetails);
                progressDialog.setMessage("Confirming Ticket...");
                progressDialog.show();

                Intent intent=new Intent(getApplicationContext(), RConfirmDetails.class);

                startActivity(intent);


            }
        });
    }
}