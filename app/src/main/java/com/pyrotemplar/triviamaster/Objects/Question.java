package com.pyrotemplar.triviamaster.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pyrotemplar on 8/11/2015.
 */
public class Question{
    String questionID;
    String questionText;
    String questionOption1;
    String questionOption2;
    String questionOption3;
    String questionOption4;
    String questionAnswer;
    String categoryID;

    public Question(){

    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionOption1() {
        return questionOption1;
    }

    public void setQuestionOption1(String questionOption1) {
        this.questionOption1 = questionOption1;
    }

    public String getQuestionOption2() {
        return questionOption2;
    }

    public void setQuestionOption2(String questionOption2) {
        this.questionOption2 = questionOption2;
    }

    public String getQuestionOption3() {
        return questionOption3;
    }

    public void setQuestionOption3(String questionOption3) {
        this.questionOption3 = questionOption3;
    }

    public String getQuestionOption4() {
        return questionOption4;
    }

    public void setQuestionOption4(String questionOption4) {
        this.questionOption4 = questionOption4;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }


    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}