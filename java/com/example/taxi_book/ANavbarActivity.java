package com.example.taxi_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ANavbarActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth firebaseAuth;

    private Button Vehicle;
    private Button Drivers;
    private Button Rides;
    private Button Allocated_Drivers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anavbar);
        Vehicle= (Button) findViewById(R.id.vehicle);
        Drivers = (Button) findViewById(R.id.drivers);
        Allocated_Drivers = (Button) findViewById(R.id.allocated);
        Rides = (Button) findViewById(R.id.Rides);
        firebaseAuth = FirebaseAuth.getInstance();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.Dashboard){
                    Intent h = new Intent(ANavbarActivity.this, AdashboardActivity.class);
                    startActivity(h);
                } else if (id == R.id.setting) {
                    Intent s = new Intent(ANavbarActivity.this, AsettingActivity.class);
                    startActivity(s);
                } else if (id == R.id.cancel) {
                    Intent c = new Intent(ANavbarActivity.this,AcancelActivity.class);
                    startActivity(c);
                } else if (id == R.id.contact) {
                    Intent co = new Intent(ANavbarActivity.this, AcontactActivity.class);
                    startActivity(co);
                    finish();
                    startActivity(new Intent(ANavbarActivity.this,ALoginActivity.class));
                }
                return true;
            }
        });

        Vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AvehicleActivity.class));
            }
        });
        Drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdriversActivity.class));
            }
        });
        Rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AridesActivity.class));
            }
        });
        Allocated_Drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AallocationActivity.class));
            }
        });
    }
}
