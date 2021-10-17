package com.example.taxi_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdashboardActivity  extends AppCompatActivity implements OnClickListener {

    TextView cardVehicle;
    TextView cardRiders;
    TextView cardDrivers;
    TextView cardAllocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adashboard);

        cardVehicle = findViewById(R.id.cardvehicle);
        cardRiders = findViewById(R.id.cardriders);
        cardDrivers = findViewById(R.id.carddrivers);
        cardAllocation = findViewById(R.id.cardallocation);

        cardVehicle.setOnClickListener(this);

        cardAllocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.cardallocation) {
                    startActivity(new Intent(AdashboardActivity .this, AallocationdetailsActivity.class));
                }
                Toast.makeText(AdashboardActivity .this,"Allocation Details",Toast.LENGTH_SHORT).show();
            }
        });
        cardDrivers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.carddrivers) {
                    startActivity(new Intent(AdashboardActivity .this, DriverDataActivity.class));
                }
                Toast.makeText(AdashboardActivity .this,"Drivers Details",Toast.LENGTH_SHORT).show();
            }
        });
        cardRiders.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.cardriders) {
                    startActivity(new Intent(AdashboardActivity .this, ARidersDetailsActivity.class));
                }
                Toast.makeText(AdashboardActivity .this, "Riders Details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cardvehicle) {
            startActivity(new Intent(this, AvehicleviewActivity.class));
        }
        Toast.makeText(AdashboardActivity .this, "Vehicle Details", Toast.LENGTH_SHORT).show();
    }
}
