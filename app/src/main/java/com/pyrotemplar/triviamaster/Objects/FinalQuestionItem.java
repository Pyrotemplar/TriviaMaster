package com.pyrotemplar.triviamaster.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pyrotemplar on 8/17/2015.
 */
public class FinalQuestionItem implements Parcelable {
    String questionText;
    String rightAnswer;
    String userAnswer;

    public FinalQuestionItem() {
    }

    // Your existing methods go here. (There is no need for me to re-write them.)

    // The following methods that are required for using Parcelable
    private FinalQuestionItem(Parcel in) {
        // This order must match the order in writeToParcel()
        questionText = in.readString();
        rightAnswer = in.readString();
        userAnswer = in.readString();


        // Continue doing this for the rest of your member data
    }

    public void writeToParcel(Parcel out, int flags) {
        // Again this order must match the Question(Parcel) constructor
        out.writeString(questionText);
        out.writeString(rightAnswer);
        out.writeString(userAnswer);


        // Again continue doing this for the rest of your member data
    }

    // Just cut and paste this for now
    public int describeContents() {
        return 0;
    }

    // Just cut and paste this for now
    public static final Parcelable.Creator<FinalQuestionItem> CREATOR = new Parcelable.Creator<FinalQuestionItem>() {
        public FinalQuestionItem createFromParcel(Parcel in) {
            return new FinalQuestionItem(in);
        }

        public FinalQuestionItem[] newArray(int size) {
            return new FinalQuestionItem[size];
        }
    };

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

}
