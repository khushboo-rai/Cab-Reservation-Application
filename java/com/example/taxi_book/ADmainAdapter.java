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
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;


public class ADmainAdapter extends FirebaseRecyclerAdapter<ADmodel,ADmainAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ADmainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<ADmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ADmainAdapter.myViewHolder holder, int position, @NonNull @NotNull ADmodel model) {

        holder.Email.setText(model.getEmail());
        holder.Name.setText(model.getName());
        holder.Phone_Number.setText(model.getPhone_Number());
        holder.Address.setText(model.getAddress());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.Email.getContext())
                        .setContentHolder(new ViewHolder(R.layout.driver_update))
                        .setExpanded(true,1500)
                        .create();

                View view = dialogPlus.getHolderView();
                EditText Email = view.findViewById(R.id.id);
                EditText Name = view.findViewById(R.id.name);
                EditText Phone_Number = view.findViewById(R.id.number);
                EditText Address = view.findViewById(R.id.address);

                Button update = view.findViewById(R.id.update);

                Email.setText(model.getEmail());
                Name.setText(model.getName());
                Phone_Number.setText(model.getPhone_Number());
                Address.setText(model.getAddress());
                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("email",Email.getText().toString());
                        map.put("name",Name.getText().toString());
                        map.put("phone_Number",Phone_Number.getText().toString());
                        map.put("address",Address.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("DriversDetails")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Email.getContext(),"Updated $",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.Email.getContext(),"Error while updating",Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Email.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("DriversDetails")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.Email.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView Email,Name,Phone_Number,Address;
        Button edit,delete;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            Email=(TextView)itemView.findViewById(R.id.id);
            Name=(TextView)itemView.findViewById(R.id.name);
            Phone_Number=(TextView)itemView.findViewById(R.id.number);
            Address=(TextView)itemView.findViewById(R.id.address);

            edit=(Button)itemView.findViewById(R.id.edit);
            delete=(Button)itemView.findViewById(R.id.delete);

        }
    }
}
