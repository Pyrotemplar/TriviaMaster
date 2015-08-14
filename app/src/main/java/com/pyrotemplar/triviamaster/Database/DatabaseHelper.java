package com.pyrotemplar.triviamaster.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pyrotemplar on 8/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final String LOG_TAG = DatabaseHelper.class.getSimpleName();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TriviaMasterDataBase.db";
    public static final String QUESTION_TABLE = "Questions";
    public static final String QUESTION_ID = "ID";
    public static final String QUESTION_TEXT = "Question";
    public static final String QUESTION_OPTION1 = "Option_1";
    public static final String QUESTION_OPTION2 = "Option_2";
    public static final String QUESTION_OPTION3 = "Option_3";
    public static final String QUESTION_OPTION4 = "Option_4";
    public static final String QUESTION_ANSWER = "Answer";
    public static final String CATEGORY_ID = "Category_ID";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + QUESTION_TABLE + "(" + QUESTION_ID
            + " integer primary key, " + QUESTION_TEXT
            + " text not null, " + QUESTION_OPTION1
            + " text not null, " + QUESTION_OPTION2
            + " text not null, " + QUESTION_OPTION3
            + " text not null, " + QUESTION_OPTION4
            + " text not null, " + QUESTION_ANSWER
            + " text not null, " + CATEGORY_ID
            + " integer not null);";

    private static DatabaseHelper instance;
    public synchronized static DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(LOG_TAG,"Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
        onCreate(db);
    }
}
