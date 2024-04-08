package com.shaihi.notes_app_sqlite;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    NotesModel notesModel = new NotesModel();
    RecyclerView recyclerView;

    int lastNumberOfItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupResources();
        recyclerView.setAdapter(new AddItemAdapter(this, notesModel));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lastNumberOfItems != NotesModel.notes.size()) {
            recyclerView.getAdapter().notifyItemInserted(NotesModel.notes.size()-1);
        }

    }

    private void setupResources() {
        fab = findViewById(R.id.addItemBtn);
        recyclerView = findViewById(R.id.itemsList);

        fab.setOnClickListener(v -> {
            lastNumberOfItems = notesModel.notes.size();
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
        });
    }
}