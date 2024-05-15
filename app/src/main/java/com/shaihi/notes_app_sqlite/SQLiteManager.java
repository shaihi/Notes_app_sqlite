package com.shaihi.notes_app_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Notes";
    private static final String COUNTER = "counter";

    private static final String ID_FIELD = "id";
    private static final String FIRSTNAME_FIELD = "fname";
    private static final String LASTNAME_FIELD = "lname";
    private static final String AGE_FIELD = "age";
    private static final String DELETED_FIELD = "deleted";

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This is called a Singleton. It means there is only one instance of the class in the whole app
    //Why would you need it with this type or resource?
    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append(" (")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(FIRSTNAME_FIELD)
                .append(" TEXT, ")
                .append(LASTNAME_FIELD)
                .append(" TEXT, ")
                .append(AGE_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        sqLiteDatabase.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on changing the DB. We are not implementing this for now
    }

    public void addNoteToDB(NotesModel.NoteItem note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FIELD, note.getId());
        values.put(FIRSTNAME_FIELD, note.getFirstName());
        values.put(LASTNAME_FIELD, note.getLastName());
        values.put(AGE_FIELD, note.getAge());
        values.put(DELETED_FIELD, note.isDeleted() ? "deleted" : "");

        db.insert(TABLE_NAME, null, values);
    }

    public void updateNoteToDB(NotesModel.NoteItem note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FIELD, note.getId());
        values.put(FIRSTNAME_FIELD, note.getFirstName());
        values.put(LASTNAME_FIELD, note.getLastName());
        values.put(AGE_FIELD, note.getAge());
        values.put(DELETED_FIELD, note.isDeleted() ? "deleted" : "");

        //Here we are asking to update the table with TABLE_NAME with the values in values. We
        //only update the lines in the table WHERE ID equals the values in the string array that is
        // passed in the last argument
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(note.getId())});
    }

    public void populateNotesFromDB(NotesModel notesModel) {
        SQLiteDatabase db = getReadableDatabase();

        //we try to get all the notes from the DB
        try (Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            //The results are given back an Object called Cursor that let's us iterate over all the results
            // Read more about Cursor at https://developer.android.com/reference/android/database/Cursor
            while (result.moveToNext()) {
                //The table we setup has 5 columns and this is how we can access each item in the row
                int id = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String age = result.getString(4);
                String stringDeleted = result.getString(5);
                boolean deleted = !stringDeleted.isEmpty();
                NotesModel.NoteItem note = new NotesModel.NoteItem(firstName, lastName, age, id, deleted);
                notesModel.notes.add(note);
            }
        } catch (SQLiteException e) {
            // handle exception, e.g. log error message
            Log.e("SQLiteManager", "Error executing query", e);
        } catch (CursorIndexOutOfBoundsException e) {
            // handle exception, e.g. log error message
            Log.e("SQLiteManager", "There was a problem reading one of the fields", e);
        }
    }
}
