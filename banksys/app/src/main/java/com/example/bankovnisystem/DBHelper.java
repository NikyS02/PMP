package com.example.bankovnisystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BankApp.db";
    private static final String SQL_CREATE_TABLE_BankAcc = "CREATE TABLE IF NOT EXISTS BankAcc (AccNumber int PRIMARY KEY, login string NOT NULL, passwd string NOT NULL, name string NOT NULL, balance double NOT NULL);";
    private static final String SQL_CREATE_TABLE_Payment = "CREATE TABLE IF NOT EXISTS Payment (AccNumber int PRIMARY KEY, bankCode int NOT NULL, ammout double NOT NULL, int VS, int SS, int KS, messageForReciever string NOT NULL, messageForSender string NOT NULL, date string NOT NULL);";
    //private static final String SQL_DELETE_ENTRIES = "DELETE TABLE BankAcc; DELETE TABLE Payment";
    private static final String SQL_INSERT_BankAcc = "INSERT INTO BankAcc VALUES(111111, 'login', '1234', 'Test Acc', 4200.0);";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean checkLogin(String login, String passwd) {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("SELECT * FROM BankAcc WHERE login ='" + login + "' AND passwd = '" + passwd + "'");
        /*if() {
            return true;
        }
        return false;*/
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_BankAcc);
        db.execSQL(SQL_CREATE_TABLE_Payment);
        db.execSQL(SQL_INSERT_BankAcc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }

    public void writePaymentIntoDB(SQLiteDatabase db , int accNumber,
                                   int bankCode,
                                   double ammout,
                                   int VS,
                                   int SS,
                                   int KS,
                                   String messageForReciever,
                                   String messageForSender,
                                   String date) {
        String Insert = "INSERT INTO Payment VALUES(" + accNumber + ", " + bankCode + ", " + ammout + ", " + VS + ", " + SS + ", " + KS + ", " + messageForReciever + ", " + messageForSender + ", " + date + ");";
        db.execSQL(Insert);


    }
}
