package com.pyrotemplar.triviamaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pyrotemplar.triviamaster.Database.QuestionDAO;
import com.pyrotemplar.triviamaster.Objects.Question;
import com.pyrotemplar.triviamaster.R;

import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionTextView;
    RadioButton answerButton1;
    RadioButton answerButton2;
    RadioButton answerButton3;
    RadioButton answerButton4;
    Button submitButton;
    Button skipButton;

    QuestionDAO questionDAO;
    List<Question> listOfQuestions;
    Question question;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);



        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerButton1 = (RadioButton) findViewById(R.id.answerButton1);
        answerButton2 = (RadioButton) findViewById(R.id.answerButton2);
        answerButton3 = (RadioButton) findViewById(R.id.answerButton3);
        answerButton4 = (RadioButton) findViewById(R.id.answerButton4);
        skipButton = (Button) findViewById(R.id.skipButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        skipButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        questionDAO = new QuestionDAO(this);
        questionDAO.open();

        listOfQuestions = questionDAO.getListOfQuestions(26, 10);

        questionDAO.close();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitButton) {
            if (i < 10) {
                NewQuestion();
                i++;

            } else {
                i = 0;
            }

        } else if (v.getId() == R.id.skipButton) {
            Intent intent = new Intent();
            intent.putExtra("test", "test1");
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void NewQuestion() {
        question = listOfQuestions.get(i);
        questionTextView.setText(question.getQuestion_Text());
        answerButton1.setText(question.getQuestion_option1());
        answerButton2.setText(question.getQuestion_option2());
        answerButton3.setText(question.getQuestion_option3());
        answerButton4.setText(question.getQuestion_option4());
    }
}
