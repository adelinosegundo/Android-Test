package com.android_dev.profile.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adelinosegundo on 9/28/15.
 */
public class UserOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "profiles";
    private static final String USERS_TABLE_NAME = "users";

    private static final String USERS_TABLE_CREATE =
            "CREATE TABLE " + USERS_TABLE_NAME + " (" +
                    "id INTEGER NOT NULL PRIMARY KEY autoincrement, " +
                    "name TEXT, " +
                    "description TEXT);";

    public UserOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
