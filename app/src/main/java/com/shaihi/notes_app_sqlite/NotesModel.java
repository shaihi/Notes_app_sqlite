package com.shaihi.notes_app_sqlite;

import java.util.ArrayList;

public class NotesModel {
    static public ArrayList<NoteItem> notes = new ArrayList<>();

    public void addNoteItem(String firstName, String lastName, String age) {
        notes.add(new NoteItem(firstName, lastName, age, notes.size(), false));
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

        private boolean isDeleted = false;

        private int id;

        public NoteItem(String firstName, String lastName, String age, int id, boolean isDeleted) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.id = id;
            this.isDeleted = isDeleted;
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

        public int getId() {
            return id;
        }
        public boolean isDeleted() {
            return isDeleted;
        }
    }
}
