package com.pyrotemplar.triviamaster.Objects;

/**
 * Created by Pyrotemplar on 8/11/2015.
 */
public class Question {
    String question_id;
    String question_Text;
    String question_option1;
    String question_option2;
    String question_option3;
    String question_option4;
    String question_answer;
    String category_id;

    public Question() {
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_Text() {
        return question_Text;
    }

    public void setQuestion_Text(String question_Text) {
        this.question_Text = question_Text;
    }

    public String getQuestion_option1() {
        return question_option1;
    }

    public void setQuestion_option1(String question_option1) {
        this.question_option1 = question_option1;
    }

    public String getQuestion_option2() {
        return question_option2;
    }

    public void setQuestion_option2(String question_option2) {
        this.question_option2 = question_option2;
    }

    public String getQuestion_option3() {
        return question_option3;
    }

    public void setQuestion_option3(String question_option3) {
        this.question_option3 = question_option3;
    }

    public String getQuestion_option4() {
        return question_option4;
    }

    public void setQuestion_option4(String question_option4) {
        this.question_option4 = question_option4;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
