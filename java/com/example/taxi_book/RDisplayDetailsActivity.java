package com.example.taxi_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RDisplayDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Radapter Radapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AllocationDetails"), model.class)
                        .build();

        Radapter =new Radapter(options);
        recyclerView.setAdapter(Radapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Radapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Radapter.stopListening();
    }

}