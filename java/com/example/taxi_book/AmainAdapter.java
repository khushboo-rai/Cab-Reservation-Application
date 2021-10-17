package com.example.taxi_book;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AmainAdapter extends FirebaseRecyclerAdapter<Amodel,AmainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AmainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Amodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull AmainAdapter.myViewHolder holder,final int position, @NonNull @NotNull Amodel model) {
        holder.cab_id.setText(model.getCab_id());
        holder.cab_Name.setText(model.getCab_Name());
        holder.cab_Number.setText(model.getCab_Number());
        holder.owner_Name.setText(model.getOwner_Name());
        holder.cab_Condition.setText(model.getCab_Condition());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.cab_id.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1500)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText cab_id = view.findViewById(R.id.id);
                EditText cab_Name = view.findViewById(R.id.name);
                EditText cab_Number = view.findViewById(R.id.number);
                EditText owner_Name = view.findViewById(R.id.owner);
                EditText cab_Condition = view.findViewById(R.id.condition);

                Button update = view.findViewById(R.id.update);

                cab_id.setText(model.getCab_id());
                cab_Name.setText(model.getCab_Name());
                cab_Number.setText(model.getCab_Number());
                owner_Name.setText(model.getOwner_Name());
                cab_Condition.setText(model.getCab_Condition());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, java.lang.Object> map = new HashMap<>();
                       map.put("cab_id",cab_id.getText().toString());
                       map.put("cab_Name",cab_Name.getText().toString());
                       map.put("cab_Number",cab_Number.getText().toString());
                        map.put("owner_Name",owner_Name.getText().toString());
                        map.put("cab_Condition",cab_Condition.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("VehicleDetails")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.cab_id.getContext(),"Updated $",Toast.LENGTH_SHORT).show();
                                       dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.cab_id.getContext(),"Error while updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }

                                });

                    }
                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cab_id.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("VehicleDetails")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.cab_id.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        TextView cab_id,cab_Name,cab_Number,owner_Name,cab_Condition;

        Button edit,delete;
        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            cab_id=(TextView)itemView.findViewById(R.id.id);
            cab_Name=(TextView)itemView.findViewById(R.id.nametext);
            cab_Number=(TextView)itemView.findViewById(R.id.name);
            owner_Name=(TextView)itemView.findViewById(R.id.ownerName);
            cab_Condition=(TextView)itemView.findViewById(R.id.condition);

            edit=(Button)itemView.findViewById(R.id.edit);
            delete=(Button)itemView.findViewById(R.id.delete);
        }
    }

    private class Object {
    }
}
