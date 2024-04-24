
        package com.example.bankovnisystem;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.Toast;

        import androidx.annotation.Nullable;

        import java.io.Serializable;
        import java.util.ArrayList;

        public class DBHelper extends SQLiteOpenHelper implements Serializable {
    private SQLiteDatabase db = this.getWritableDatabase();
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "BankApp.db";

    private static final String SQL_INSERT_BankAcc = "INSERT INTO BankAcc VALUES(null, 111111, 'login', '1234', 'Test Acc', 4200.0);";
    private static final String SQL_INSERT_BankAcc2 = "INSERT INTO BankAcc VALUES(null, 222222, 'test', '1234', 'Test Acc2', 600.0);";
    private static final String SQL_INSERT_TestPayment = "INSERT INTO Payment VALUES(null, 111111, 420, 59.5, 0, 0, 0, 'Message for reciever', 'Message for sender', 'date');";

    public DBHelper(@Nullable Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL("CREATE TABLE BankAcc ( _id INTEGER PRIMARY KEY AUTOINCREMENT, AccNumber INTEGER, login TEXT NOT NULL, passwd TEXT NOT NULL, name TEXT NOT NULL, balance DOUBLE NOT NULL);");
        db.execSQL("CREATE TABLE Payment ( _id INTEGER PRIMARY KEY AUTOINCREMENT, AccNumber INTEGER, bankCode INTEGER NOT NULL, ammout DOUBLE NOT NULL, VS INTEGER, SS INTEGER, KS INTEGER, messageForReciever TEXT NOT NULL, messageForSender TEXT NOT NULL, date TEXT NOT NULL);");
        db.execSQL(SQL_INSERT_BankAcc);
        db.execSQL(SQL_INSERT_BankAcc2);
        db.execSQL(SQL_INSERT_TestPayment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading databáze z verze " + oldVersion + " na " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS BankAcc;");
        db.execSQL("DROP TABLE IF EXISTS Payment;");
        this.onCreate(db);
    }

    public ArrayList<BankAcc> getBankAccsFromDB() {
                Cursor BankAccsCursor = db.rawQuery("SELECT * FROM " + "BankAcc", null);

                ArrayList<BankAcc> BankAccArrayList = new ArrayList<>();
                if (BankAccsCursor.moveToFirst()) {
                    do {
                        BankAccArrayList.add(new BankAcc(BankAccsCursor.getInt(1),
                                BankAccsCursor.getInt(0),
                                BankAccsCursor.getString(2),
                                BankAccsCursor.getString(3),
                                BankAccsCursor.getString(4),
                                BankAccsCursor.getDouble(5)
                        ));
                    } while (BankAccsCursor.moveToNext());
                }
        BankAccsCursor.close();
                return BankAccArrayList;
            }

            public BankAcc checkLogin(String login, String passwd) {
                ArrayList<BankAcc> BankAccArrayList = getBankAccsFromDB();

                for(BankAcc bankAcc : BankAccArrayList){
                    if(bankAcc.getLogin().equals(login) && bankAcc.getPasswd().equals(passwd)) {
                        return bankAcc;
                    }
                }
                return null;
            }

    public void writePaymentIntoDB(Payment payment) {
        try {
            db.execSQL(
                    "INSERT INTO Payment VALUES(null,"
                            + payment.getAccNumber() + ", "
                            + payment.getBankCode() + ", "
                            + payment.getAmmout() + ", "
                            + payment.getVS() + ", "
                            + payment.getSS() + ", "
                            + payment.getKS() + ", '"
                            + payment.getMessageForReciever() + "', '"
                            + payment.getMessageForSender() + "', '"
                            + payment.getDate() + "');");
        } catch(Exception e) {
            Log.e("DBHelper - wtire", "Error executing raw query: " + e.getMessage());
        }
        Log.d("DBHelper", "PaymentWritten ");
    }

//    public ArrayList<Payment> getPayments(BankAcc bankAcc){
//        Cursor PaymentCursor = db.rawQuery("SELECT * FROM " + "Payment", null);
//
//        ArrayList<Payment> PaymentArrayList = new ArrayList<>();
//        if (PaymentCursor != null && PaymentCursor.moveToFirst()) {
//            do {
//                if (PaymentCursor.getInt(2) == bankAcc.getAccNumber()) {
//                    PaymentArrayList.add(new Payment(PaymentCursor.getInt(0),
//                            PaymentCursor.getInt(1),
//                            PaymentCursor.getInt(2),
//                            PaymentCursor.getDouble(3),
//                            PaymentCursor.getInt(4),
//                            PaymentCursor.getInt(5),
//                            PaymentCursor.getInt(6),
//                            PaymentCursor.getString(7),
//                            PaymentCursor.getString(8),
//                            PaymentCursor.getString(9)
//                    ));
//                }
//            } while (PaymentCursor.moveToNext());
//        }
//        if (PaymentCursor != null) {
//            PaymentCursor.close();
//        }
//        //PaymentCursor.close();
//        return PaymentArrayList;
//    }

            public void Close() {
                this.db.close();
            }
            
            public ArrayList<Payment> getPaymentsFromDB(BankAcc bankAcc) {
                if (bankAcc == null) {
                    return null;
                }
                String[] selectionArgs = {String.valueOf(bankAcc.getAccNumber())};
                Cursor PaymentCursor = null;
                try {
                    PaymentCursor = db.rawQuery("SELECT * FROM Payment", null);
                    //PaymentCursor = db.rawQuery("SELECT * FROM Payment WHERE AccNumber = ? ORDER BY Date DESC", selectionArgs);

                    ArrayList<Payment> PaymentArrayList = new ArrayList<>();
                    if (PaymentCursor.moveToFirst()) {
                        do {
                            // Log the AccNumber from the bankAcc object
                            Log.d("DBHelper - ", "BankAcc AccNumber: " + bankAcc.getAccNumber());

                            // Inside the loop, log the AccNumber from the PaymentCursor
                            Log.d("DBHelper - ", "PaymentCursor AccNumber: " + PaymentCursor.getInt(2));

                            if (PaymentCursor.getInt(3) == bankAcc.getAccNumber()) {
                                PaymentArrayList.add(new Payment(PaymentCursor.getInt(1),
                                        PaymentCursor.getInt(2),
                                        PaymentCursor.getInt(3),
                                        PaymentCursor.getDouble(4),
                                        PaymentCursor.getInt(5),
                                        PaymentCursor.getInt(6),
                                        PaymentCursor.getInt(7),
                                        PaymentCursor.getString(8),
                                        PaymentCursor.getString(9),
                                        PaymentCursor.getString(10)
                                ));
                            }
                        } while (PaymentCursor.moveToNext());
                    }
                    Log.d("DBHelper - ", "Got data: " + PaymentArrayList.size());
                    PaymentCursor.close();
                    return PaymentArrayList;
                } catch (Exception e) {
                    Log.e("DBHelper", "Error executing raw query: " + e.getMessage());
                    return null;
                } finally {
                    if (PaymentCursor != null) {
                        PaymentCursor.close();
                    }
                    //close();
                }


            }
}
