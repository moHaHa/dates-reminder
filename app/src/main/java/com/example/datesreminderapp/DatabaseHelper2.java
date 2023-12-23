package com.example.datesreminderapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.datesreminderapp.models.Agent;
import com.example.datesreminderapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "reminder_app_database2";
    private static final int DATABASE_VERSION = 1;

    private static final String NOTE_TABLE_NAME = "note";

    /**
     * Note Table
     */
    // Define constants for the Note table


    private static final String COLUMN_NOTE_ID = "id";

    private static final String COLUMN_NOTE_TITLE = "title";
    private static final String COLUMN_NOTE_DESCRIPTION = "description";
    private static final String COLUMN_NOTE_DATE = "date";
    private static final String COLUMN_NOTE_TIME = "time";
    private static final String COLUMN_NOTE_REMINDER_TIME = "reminder_time";
    private static final String COLUMN_NOTE_AGENT_NAME = "agent_name";
    private static final String COLUMN_NOTE_AGENT_NUMBER = "agent_number";
    private static final String COLUMN_NOTE_DEPENDENCY_NAME = "dependency_name";
    private static final String COLUMN_NOTE_IS_UPDATED = "is_updated";
    private static final String COLUMN_NOTE_STATUS = "status";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Create Agent Table
     * 1. make query
     * 2. use execSQL method for create the table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {



        String createNoteTableQuery = "CREATE TABLE " + NOTE_TABLE_NAME + " (" +
                COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE_TITLE + " TEXT, " +
                COLUMN_NOTE_DESCRIPTION + " TEXT, " +
                COLUMN_NOTE_DATE + " TEXT, " +
                COLUMN_NOTE_TIME + " TEXT, " +
                COLUMN_NOTE_REMINDER_TIME + " TEXT, " +
                COLUMN_NOTE_AGENT_NAME + " TEXT, " +
                COLUMN_NOTE_AGENT_NUMBER + " TEXT, " +
                COLUMN_NOTE_DEPENDENCY_NAME + " TEXT, " +
                COLUMN_NOTE_IS_UPDATED + " BOOLEAN, " +
                COLUMN_NOTE_STATUS + " TEXT)";

        db.execSQL(createNoteTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropNoteTableQuery = "DROP TABLE IF EXISTS " + NOTE_TABLE_NAME;
        db.execSQL(dropNoteTableQuery);
        onCreate(db);
    }



    /**
     * Insert Note
     */
    public boolean insertNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getTitle());
        values.put(COLUMN_NOTE_DESCRIPTION, note.getDescription());
        values.put(COLUMN_NOTE_DATE, note.getDate());
        values.put(COLUMN_NOTE_TIME, note.getTime());
        values.put(COLUMN_NOTE_REMINDER_TIME, note.getReminderTime());
        values.put(COLUMN_NOTE_AGENT_NAME, note.getAgentName());
        values.put(COLUMN_NOTE_AGENT_NUMBER, note.getAgentNumber());
        values.put(COLUMN_NOTE_DEPENDENCY_NAME, note.getDependencyName());
        values.put(COLUMN_NOTE_IS_UPDATED, note.isUpdated());
        values.put(COLUMN_NOTE_STATUS, note.getStatus());
        // Log the values
        Log.d("InsertNote", "Title: " + note.getTitle());
        Log.d("InsertNote", "Description: " + note.getDescription());
        Log.d("InsertNote", "Date: " + note.getDate());
        Log.d("InsertNote", "Time: " + note.getTime());
        Log.d("InsertNote", "Reminder Time: " + note.getReminderTime());
        Log.d("InsertNote", "Agent Name: " + note.getAgentName());
        Log.d("InsertNote", "Agent Number: " + note.getAgentNumber());
        Log.d("InsertNote", "Dependency Name: " + note.getDependencyName());
        Log.d("InsertNote", "Is Updated: " + note.isUpdated());
        Log.d("InsertNote", "Status: " + note.getStatus());
        long newRowId = db.insert(NOTE_TABLE_NAME, null, values);
        return newRowId != -1;
    }

    /**
     * Get List Of Notes
     */
    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = null;

        try {
            String selectAllQuery = "SELECT * FROM " + NOTE_TABLE_NAME;
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectAllQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String description = cursor.getString(2);
                    String date = cursor.getString(3);
                    String time = cursor.getString(4);
                    String reminderTime = cursor.getString(5);
                    String agentName = cursor.getString(6);
                    String agentNumber = cursor.getString(7);
                    String dependencyName = cursor.getString(8);
                    boolean isUpdated = cursor.getInt(9) == 1;
                    String status = cursor.getString(10);

                    notes.add(new Note(id, title, description, date, time, reminderTime, agentName, agentNumber, dependencyName, isUpdated, status));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DATABASE_ERROR", e.toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return notes;
    }
}