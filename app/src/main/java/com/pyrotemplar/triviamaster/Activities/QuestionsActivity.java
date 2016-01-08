package com.pyrotemplar.triviamaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplar.triviamaster.Database.QuestionDAO;
import com.pyrotemplar.triviamaster.Objects.FinalQuestionItem;
import com.pyrotemplar.triviamaster.Objects.Question;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {
    private final String LOG_TAG = QuestionsActivity.class.getSimpleName();

    TextView questionTextView;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;
    Button submitButton;
    Button skipButton;
    int numberOfQuestions = 10;
    int currentQuestion;
    int score;

    QuestionDAO questionDAO;
    ArrayList<Question> listOfQuestions;
    Question question;
    ArrayList<FinalQuestionItem> finalQuestionItemsList = new ArrayList<FinalQuestionItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerButton1 = (Button) findViewById(R.id.answerButton1);
        answerButton2 = (Button) findViewById(R.id.answerButton2);
        answerButton3 = (Button) findViewById(R.id.answerButton3);
        answerButton4 = (Button) findViewById(R.id.answerButton4);
        skipButton = (Button) findViewById(R.id.skipButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        skipButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);

        questionDAO = new QuestionDAO(this);
        questionDAO.open();
        listOfQuestions = questionDAO.getListOfQuestions(26, 10);
        questionDAO.close();

        currentQuestion = 0;
        score = 0;
        NewQuestion(currentQuestion);

        updateUI();

    }

    private void questionLogic() {
        String correctAnswer = "";
        String userAnswer = "";
        FinalQuestionItem finalQuestionItem = new FinalQuestionItem();

        if (!answerButton1.isEnabled()) {
            userAnswer = answerButton1.getText().toString();
        } else if (!answerButton2.isEnabled()) {
            userAnswer = answerButton2.getText().toString();
        } else if (!answerButton3.isEnabled()) {
            userAnswer = answerButton3.getText().toString();
        } else if (!answerButton4.isEnabled()) {
            userAnswer = answerButton4.getText().toString();
        } else {
            userAnswer = "";
        }


        switch (Integer.parseInt(question.getQuestionAnswer())) {
            case 1:
                correctAnswer = answerButton1.getText().toString();
                break;
            case 2:
                correctAnswer = answerButton2.getText().toString();
                break;
            case 3:
                correctAnswer = answerButton3.getText().toString();
                break;
            case 4:
                correctAnswer = answerButton4.getText().toString();
                break;
        }

        if (correctAnswer.equals(userAnswer)) {
            score++;
        }
        finalQuestionItem.setRightAnswer(correctAnswer);
        finalQuestionItem.setUserAnswer(userAnswer);
        finalQuestionItem.setQuestionText(question.getQuestionText());
        finalQuestionItemsList.add(finalQuestionItem);
        if (currentQuestion < numberOfQuestions) {
            NewQuestion(currentQuestion);
        } else {
            finalScore();
        }
    }

    private void updateUI() {

        answerButton1.setEnabled(true);
        answerButton2.setEnabled(true);
        answerButton3.setEnabled(true);
        answerButton4.setEnabled(true);

        questionTextView.setText(question.getQuestionText());
        answerButton1.setText(question.getQuestionOption1());
        answerButton2.setText(question.getQuestionOption2());
        answerButton3.setText(question.getQuestionOption3());
        answerButton4.setText(question.getQuestionOption4());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // add diolog promp for users to go back to the category list

        Toast.makeText(QuestionsActivity.this, "Will reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.answerButton1) {
            answerButton1.setEnabled(false);
           // answerButton1.setBackground(getResources().getColor(R.color.colorPrimaryLight));
            answerButton2.setEnabled(true);
            answerButton3.setEnabled(true);
            answerButton4.setEnabled(true);
        } else if (v.getId() == R.id.answerButton2) {
            answerButton1.setEnabled(true);
            answerButton2.setEnabled(false);
            answerButton3.setEnabled(true);
            answerButton4.setEnabled(true);
        } else if (v.getId() == R.id.answerButton3) {
            answerButton1.setEnabled(true);
            answerButton2.setEnabled(true);
            answerButton3.setEnabled(false);
            answerButton4.setEnabled(true);
        } else if (v.getId() == R.id.answerButton4) {
            answerButton1.setEnabled(true);
            answerButton2.setEnabled(true);
            answerButton3.setEnabled(true);
            answerButton4.setEnabled(false);
        }

        if (v.getId() == R.id.submitButton) {
            questionLogic();
            updateUI();
        } else if (v.getId() == R.id.skipButton) {
            questionLogic();
            updateUI();
        }

    }


    private void finalScore() {
        Intent intent = new Intent();
        intent.putExtra("score", score);
        intent.putExtra("numberOfQuestions", numberOfQuestions);
        intent.putParcelableArrayListExtra("finalQuestionItemsList", finalQuestionItemsList);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void NewQuestion(int position) {
        question = listOfQuestions.get(position);
        currentQuestion++;
    }

    private void toggleButtons() {
        if (answerButton1.isEnabled())
            answerButton1.setEnabled(false);
        else
            answerButton1.setEnabled(true);

        if (answerButton2.isEnabled())
            answerButton2.setEnabled(false);
        else
            answerButton2.setEnabled(false);

        if (answerButton3.isEnabled())
            answerButton3.setEnabled(false);
        else
            answerButton3.setEnabled(true);

        if (answerButton4.isEnabled())
            answerButton4.setEnabled(false);
        else
            answerButton4.setEnabled(true);

    }
}
