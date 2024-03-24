package com.example.rolldiceapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_ID =  "id";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recordsDB";
    private static final String TABLE_RECORDS = "records";
    public static final String KEY_ROLL_VALUE = "roll_value";
    private static final String KEY_RESULT = "result";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the db table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORDS_TABLE = "CREATE TABLE " + TABLE_RECORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ROLL_VALUE + " INTEGER,"
                + KEY_RESULT + " INTEGER" + ")";
        db.execSQL(CREATE_RECORDS_TABLE);
    }

    // Upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

    // Add a new roll entry
    public void addRoll(int rollNumber, int result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROLL_VALUE, rollNumber);
        values.put(KEY_RESULT, result);
        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    // Get all records
    public Cursor getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RECORDS, null);
    }
}
