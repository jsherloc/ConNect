package com.example.james.connect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.james.connect.ContactManagementContract.*;

/**
 * Created by james on 02/03/2018.
 */

public class ContactManagementDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ContactManagement.db";
    public static final int DATABASE_VERSION = 1;

    public ContactManagementDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + User.TABLE_NAME + " (" +
                User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                User.COLUMN_USER_NAME + " TEXT NOT NULL," +
                User.COLUMN_USER_NUMBER + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
