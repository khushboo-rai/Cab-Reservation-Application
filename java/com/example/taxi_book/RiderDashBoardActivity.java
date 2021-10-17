package com.example.taxi_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class RiderDashBoardActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = "RiderDashBoardActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    private DrawerLayout mDrawerLayout;
    private FirebaseAuth firebaseAuth;
    private ActionBarDrawerToggle mToggle;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private Spinner spinner1;
    private TextView tvDate;
    private Button angry_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_dash_board);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        tvDate = (TextView) findViewById(R.id.tvDate);
        angry_btn = (Button) findViewById(R.id.angry_btn);

        progressDialog = new ProgressDialog(this);
        angry_btn.setOnClickListener(this);
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"To", "Dibrugarh Bazar", "Tinsukia", "Sivasagar", "chabua", "Dikom", "Moran", "Dibrugarh University"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);





        angry_btn.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RLoginActivity.class));
        }


        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RiderDashBoardActivity.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , mDatesetListener
                        , year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDatesetListener = new DatePickerDialog.OnDateSetListener() {
            private DatePicker view;
            private int year;
            private int month;
            private int dayOfMonth;


            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Log.d(TAG, "OnDateSet:date :" + day + "/" + (month + 1) + "/" + year);
                String date = day + "/" + (month + 1) + "/" + year;
                // String status="Journey Date";
                // mDisplayDate.setText(status+"\n"+date);
                mDisplayDate.setText(date);
            }
        };
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void searchTaxi() {


        String to = spinner1.getSelectedItem().toString().trim();


        String date = tvDate.getText().toString().trim();
        // String key = databaseReference.push().getKey();
        if (TextUtils.equals(to, "TO")) {
            //password is empty
            Toast.makeText(this, "Please select destination place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            //password is empty
            Toast.makeText(this, "Please select journey date", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Searching Taxis Please Wait...");
        progressDialog.show();

        
        SearchingDetails searchingDetails= new SearchingDetails(to,date);
        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        databaseReference.child(user1.getUid()).child("AllocationDetails").setValue("searchingDetails");
        //child("bookings").child(userId);
        String ToDetail = spinner1.getSelectedItem().toString().trim();
        Intent intent=new Intent(RiderDashBoardActivity.this, RDisplayDetailsActivity.class);
//        startActivity(new Intent(getApplicationContext(), BusActivity.class));
        intent.putExtra("TO",to);
        intent.putExtra("DATE",date);
        startActivity(intent);
        progressDialog.dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.profile:
                Intent h = new Intent(RiderDashBoardActivity.this, RProfileActivity.class);
                startActivity(h);
                break;
            case R.id.share:
                Intent l = new Intent(RiderDashBoardActivity.this, RShareActivity.class);
                startActivity(l);
                break;
            case R.id.setting:
                Intent s = new Intent(RiderDashBoardActivity.this, RSettingActivity.class);
                startActivity(s);
                break;
            case R.id.logout:
//                Intent lo = new Intent(NavigationActivity.this, LogoutActivity.class);
//                startActivity(lo);
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, RLoginActivity.class));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == angry_btn){
            searchTaxi();
        }
    }

    private class SearchingDetails {
        public SearchingDetails(String to, String date) {
        }
    }

}