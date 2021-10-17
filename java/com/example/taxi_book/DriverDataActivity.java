package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DriverDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ADmainAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_data);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ADmodel> options =
                new FirebaseRecyclerOptions.Builder<ADmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DriversDetails"), ADmodel.class)
                        .build();

        Adapter =new ADmainAdapter(options);
        recyclerView.setAdapter(Adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }
}