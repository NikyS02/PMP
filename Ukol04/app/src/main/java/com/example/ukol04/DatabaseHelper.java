package com.example.ukol04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ukol4database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "games";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAZEV = "nazev";
    public static final String COLUMN_ZANR = "zanr";
    public static final String COLUMN_DOHRANO = "dohrano";
    public static final String COLUMN_ODEHRANO = "odehrano";
    public static final String COLUMN_POSTUP = "postup";
    public static final String COLUMN_HODNOCENI = "hodnoceni";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAZEV + " TEXT, " + COLUMN_ZANR + " TEXT, " + COLUMN_DOHRANO + " TEXT, " + COLUMN_ODEHRANO + " INTEGER, " + COLUMN_POSTUP + " INTEGER, " + COLUMN_HODNOCENI + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addGame(Hra hra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAZEV, hra.getNazev());
        values.put(COLUMN_ZANR, hra.getZanr());
        values.put(COLUMN_DOHRANO, hra.isDohrano());
        values.put(COLUMN_ODEHRANO, hra.getOdehrano());
        values.put(COLUMN_POSTUP, hra.getPostup());
        values.put(COLUMN_HODNOCENI, hra.getHodnoceni());
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public int updateGame(long gameId, Hra hra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAZEV, hra.getNazev());
        values.put(COLUMN_ZANR, hra.getZanr());
        values.put(COLUMN_DOHRANO, hra.isDohrano());
        values.put(COLUMN_ODEHRANO, hra.getOdehrano());
        values.put(COLUMN_POSTUP, hra.getPostup());
        values.put(COLUMN_HODNOCENI, hra.getHodnoceni());
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(gameId)});
        db.close();
        return rowsAffected;
    }

    public int deleteGame(long gameId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(gameId)});
        db.close();
        return rowsDeleted;
    }

    public Cursor getAllGames() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Hra getGameById(long gameId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(gameId)}, null, null, null);
        if (cursor.moveToFirst()) {
            Hra hra = new Hra(cursor);
            cursor.close();
            return hra;
        } else {
            cursor.close();
            return null;
        }
    }
}
