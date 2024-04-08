package com.shaihi.notes_app_sqlite;

import java.util.ArrayList;

public class NotesModel {
    static public ArrayList<NoteItem> notes = new ArrayList<>();

    public void addNoteItem(String firstName, String lastName, String age) {
        notes.add(new NoteItem(firstName, lastName, age));
    }

    public int getNotesCount() {
        return notes.size();
    }

    public NoteItem get(int position) {
        return notes.get(position);
    }

    public ArrayList<NoteItem> getNotes() {
        return notes;
    }

    public static class NoteItem {
        private String firstName;
        private String lastName;
        private String age;

        public NoteItem(String title, String description, String date) {
            this.firstName = title;
            this.lastName = description;
            this.age = date;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAge() {
            return age;
        }
    }
}
