package com.example.taxi_book;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class Radapter extends FirebaseRecyclerAdapter<model, Radapter.myviewholder>
{
    public Radapter(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull Radapter.myviewholder holder, int position, @NonNull @NotNull model model)
    {
        holder.Vehicle_Number.setText(model.getCab_Number());
        holder.Vehicle_Name.setText(model.getCab_Name());
        holder.Driver_Name.setText(model.getDriver_Name());
        holder.Driver_Number.setText(model.getDriver_Number());
        holder.Vehicle_Condition.setText(model.getCab_Condition());

        holder.Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.Booking.getContext(),RBookingDetailsActivity.class);
                holder.Booking.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView Vehicle_Number,Vehicle_Name,Driver_Name,Driver_Number,Vehicle_Condition;

        Button Booking;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            Vehicle_Number=(TextView)itemView.findViewById(R.id.id);
            Vehicle_Name=(TextView)itemView.findViewById(R.id.nametext);
            Driver_Name=(TextView)itemView.findViewById(R.id.name);
            Driver_Number=(TextView)itemView.findViewById(R.id.number);
            Vehicle_Condition=(TextView)itemView.findViewById(R.id.condition);

            Booking=(Button)itemView.findViewById(R.id.book);

        }
    }
}
