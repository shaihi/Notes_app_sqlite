package com.shaihi.notes_app_sqlite;

import static com.shaihi.notes_app_sqlite.NotesModel.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddItem extends AppCompatActivity {

    FloatingActionButton saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        saveBtn = findViewById(R.id.saveBtn);
           saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Save note to database
                    saveNote(v);
                    }
            });
    }

    //The onClick method for the save button
    public void saveNote(View view){
        EditText firstName = findViewById(R.id.enterFirstName);
        String firstNameStr = firstName.getText().toString();
        EditText lastName = findViewById(R.id.enterLastName);
        String lastNameStr = lastName.getText().toString();
        EditText age = findViewById(R.id.enterAge);
        String ageStr = age.getText().toString();
        // Save note to database
        NotesModel.NoteItem item = new NotesModel.NoteItem(firstNameStr, lastNameStr, ageStr, notes.size(), false);
        NotesModel.notes.add(item);
        SQLiteManager.instanceOfDatabase(this).addNoteToDB(item);
        finish();
    }
}