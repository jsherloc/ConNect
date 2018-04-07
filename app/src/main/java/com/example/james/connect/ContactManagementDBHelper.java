package com.example.james.connect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.james.connect.ContactManagementContract.*;

/**
 * Created by james on 02/03/2018. James will continue with this section.
 */

public class ContactManagementDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ContactManagement.db";
    public static final int DATABASE_VERSION = 1;

    public ContactManagementDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.setForeignKeyConstraintsEnabled(true);
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + User.TABLE_NAME + " (" +
                User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                User.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                User.COLUMN_USER_NUMBER + " INTEGER UNIQUE NOT NULL);";

        final String SQL_CREATE_REPORTED_NUMBERS_TABLE = "CREATE TABLE " + ReportedNumbers.TABLE_NAME + " (" +
                ReportedNumbers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReportedNumbers.COLUMN_REPORTED_NUMBER + " INTEGER NOT NULL);";

        final String SQL_CREATE_BLOCKED_NUMBERS_TABLE = "CREATE TABLE " + BlockedNumbers.TABLE_NAME + " (" +
                BlockedNumbers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BlockedNumbers.COLUMN_BLOCKED_NUMBER + " INTEGER NOT NULL);";

        final String SQL_CREATE_PERMITTED_COUNTRIES_TABLE = "CREATE TABLE " + PermittedCountries.TABLE_NAME + " (" +
                PermittedCountries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PermittedCountries.COLUMN_COUNTRY_NAME + " TEXT UNIQUE NOT NULL, " +
                PermittedCountries.COLUMN_COUNTRY_CALLING_CODE + " INTEGER NOT NULL, " +
                PermittedCountries.COLUMN_INTERNATIONAL_DIALING_PREFIX + " INTEGER NOT NULL);";

        final String SQL_CREATE_USER_REPORTED_NUMBERS_TABLE = "CREATE TABLE " + User_ReportedNumbers.TABLE_NAME + " (" +
                User_ReportedNumbers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                User_ReportedNumbers.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                User_ReportedNumbers.COLUMN_REPORTED_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + User_ReportedNumbers.COLUMN_USER_ID + ") REFERENCES " + User.TABLE_NAME + "(" + User._ID + ")," +
                "FOREIGN KEY (" + User_ReportedNumbers.COLUMN_REPORTED_ID + ") REFERENCES " + ReportedNumbers.TABLE_NAME + "(" + ReportedNumbers._ID + ");";

        final String SQL_CREATE_USER_BLOCKED_NUMBERS_TABLE = "CREATE TABLE " + User_BlockedNumbers.TABLE_NAME + " (" +
                User_BlockedNumbers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                User_BlockedNumbers.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                User_BlockedNumbers.COLUMN_BLOCKED_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + User_BlockedNumbers.COLUMN_USER_ID + ") REFERENCES " + User.TABLE_NAME + "(" + User._ID + ")," +
                "FOREIGN KEY (" + User_BlockedNumbers.COLUMN_BLOCKED_ID + ") REFERENCES " + BlockedNumbers.TABLE_NAME + "(" + BlockedNumbers._ID + ");";

        final String SQL_CREATE_USER_PERMITTED_COUNTRIES_TABLE = "CREATE TABLE " + User_PermittedCountries.TABLE_NAME + " (" +
                User_PermittedCountries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                User_PermittedCountries.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                User_PermittedCountries.COLUMN_COUNTRY_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + User_PermittedCountries.COLUMN_USER_ID + ") REFERENCES " + User.TABLE_NAME + "(" + User._ID + ")," +
                "FOREIGN KEY (" + User_PermittedCountries.COLUMN_COUNTRY_ID + ") REFERENCES " + PermittedCountries.TABLE_NAME + "(" + PermittedCountries._ID + ");";

        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_REPORTED_NUMBERS_TABLE);
        db.execSQL(SQL_CREATE_BLOCKED_NUMBERS_TABLE);
        db.execSQL(SQL_CREATE_PERMITTED_COUNTRIES_TABLE);
        /*db.execSQL(SQL_CREATE_USER_REPORTED_NUMBERS_TABLE);
        db.execSQL(SQL_CREATE_USER_BLOCKED_NUMBERS_TABLE);
        db.execSQL(SQL_CREATE_USER_PERMITTED_COUNTRIES_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_PermittedCountries.TABLE_NAME + ";" +
                    " DROP TABLE IF EXISTS " + User_ReportedNumbers.TABLE_NAME + ";" +
                    " DROP TABLE IF EXISTS " + User.TABLE_NAME + ";" +
                    " DROP TABLE IF EXISTS " + PermittedCountries.TABLE_NAME + ";");
                //" DROP TABLE IF EXISTS " + BlockedNumbers
        onCreate(db);
    }
}
