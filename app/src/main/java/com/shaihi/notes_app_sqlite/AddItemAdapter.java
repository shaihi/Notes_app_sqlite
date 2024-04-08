package com.shaihi.notes_app_sqlite;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.MyViewHolder>{
    Context context;
    NotesModel models;

    public AddItemAdapter(Context context, NotesModel models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AddItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddItemAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.tvAge.setText(models.get(position).getAge());
        myViewHolder.tvName.setText(models.get(position).getFirstName());
        myViewHolder.tvLastName.setText(models.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return models.getNotesCount();
    }

    static public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvLastName;
        TextView tvAge;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.firstName);
            tvLastName = itemView.findViewById(R.id.lastName);
            tvAge = itemView.findViewById(R.id.age);
        }
    }
}
