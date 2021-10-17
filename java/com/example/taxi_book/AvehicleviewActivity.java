package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AvehicleviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AmainAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avehicleview);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Amodel> options =
                new FirebaseRecyclerOptions.Builder<Amodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("VehicleDetails"), Amodel.class)
                        .build();

        Adapter =new AmainAdapter(options);
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