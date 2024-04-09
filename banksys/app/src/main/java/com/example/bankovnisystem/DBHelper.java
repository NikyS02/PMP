
        package com.example.bankovnisystem;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import androidx.annotation.Nullable;

        import java.io.Serializable;

        public class DBHelper extends SQLiteOpenHelper implements Serializable {
    private SQLiteDatabase db = this.getWritableDatabase();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BankApp.db";

    //private static final String SQL_DELETE_ENTRIES = "DELETE TABLE BankAcc; DELETE TABLE Payment";
    private static final String SQL_INSERT_BankAcc = "INSERT INTO BankAcc VALUES(null, 111111, 'login', '1234', 'Test Acc', 4200.0);";

    public DBHelper(@Nullable Context context)  {
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
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL("CREATE TABLE BankAcc ( _id INTEGER PRIMARY KEY AUTOINCREMENT, AccNumber INTEGER, login TEXT NOT NULL, passwd TEXT NOT NULL, name TEXT NOT NULL, balance DOUBLE NOT NULL);");
        db.execSQL("CREATE TABLE Payment ( _id INTEGER PRIMARY KEY AUTOINCREMENT, AccNumber INTEGER, bankCode INTEGER NOT NULL, ammout DOUBLE NOT NULL, VS INTEGER, SS INTEGER, KS INTEGER, messageForReciever TEXT NOT NULL, messageForSender TEXT NOT NULL, date TEXT NOT NULL);");
        db.execSQL(SQL_INSERT_BankAcc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
        Log.w(DBHelper.class.getName(), "Upgrading databáze z verze " + oldVersion + " na " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS BankAcc;");
        db.execSQL("DROP TABLE IF EXISTS Payment;");
        this.onCreate(db);
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

    public void Close() {
        this.db.close();
    }
}