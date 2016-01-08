package com.pyrotemplar.triviamaster.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.pyrotemplar.triviamaster.Objects.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pyrotemplar on 8/11/2015.
 */
public class QuestionDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // array of all the column names for questions table
    String[] columnNames = {DatabaseHelper.QUESTION_ID, DatabaseHelper.QUESTION_TEXT, DatabaseHelper.QUESTION_OPTION1, DatabaseHelper.QUESTION_OPTION2,
            DatabaseHelper.QUESTION_OPTION3, DatabaseHelper.QUESTION_OPTION4, DatabaseHelper.QUESTION_ANSWER, DatabaseHelper.CATEGORY_ID};

    public QuestionDAO(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Question retriveQuestion(int id) {

        return null;
    }

    public int AddQuestion(Question question) {

        if (question != null && !CheckIsDataAlreadyInDBorNot(DatabaseHelper.QUESTION_TABLE, DatabaseHelper.QUESTION_ID, question.getQuestionID())) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.QUESTION_ID, question.getQuestionID());
            values.put(DatabaseHelper.QUESTION_TEXT, question.getQuestionText());
            values.put(DatabaseHelper.QUESTION_OPTION1, question.getQuestionOption1());
            values.put(DatabaseHelper.QUESTION_OPTION2, question.getQuestionOption2());
            values.put(DatabaseHelper.QUESTION_OPTION3, question.getQuestionOption3());
            values.put(DatabaseHelper.QUESTION_OPTION4, question.getQuestionOption4());
            values.put(DatabaseHelper.QUESTION_ANSWER, question.getQuestionAnswer());
            values.put(DatabaseHelper.CATEGORY_ID, question.getCategoryID());


            database.insert(DatabaseHelper.QUESTION_TABLE, null, values);
            return 0;
        } else {
            return -1;
        }
    }

    public ArrayList<Question> getListOfQuestions(int category_id, int numberOfQuestions) {

        ArrayList<Question> listOfQuestions = new ArrayList<>();

        String whereClause = DatabaseHelper.CATEGORY_ID + " = ?";

        Cursor cursor = database.query(DatabaseHelper.QUESTION_TABLE, columnNames, whereClause, new String[]{String.valueOf(category_id)}, null, null, null, String.valueOf(numberOfQuestions));
       // Cursor cursor = database.query(DatabaseHelper.QUESTION_TABLE, null, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question question = CursorToQuestion(cursor);
            listOfQuestions.add(question);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfQuestions;
    }

    private Question CursorToQuestion(Cursor cursor) {

        Question question = new Question();
        question.setQuestionID(String.valueOf(cursor.getInt(0)));
        question.setQuestionText(cursor.getString(1));
        question.setQuestionOption1(cursor.getString(2));
        question.setQuestionOption2(cursor.getString(3));
        question.setQuestionOption3(cursor.getString(4));
        question.setQuestionOption4(cursor.getString(5));
        question.setQuestionAnswer(cursor.getString(6));
        question.setCategoryID(cursor.getString(7));

        return question;


    }

    private boolean CheckIsDataAlreadyInDBorNot(String TableName, String row_ID, String item_id) {

        String Query = "Select * from " + TableName + " where " + row_ID + " = " + item_id;
        Cursor cursor = database.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
