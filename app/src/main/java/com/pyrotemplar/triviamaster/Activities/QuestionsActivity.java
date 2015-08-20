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
    RadioGroup answerGroup;
    RadioButton answerButton1;
    RadioButton answerButton2;
    RadioButton answerButton3;
    RadioButton answerButton4;
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
        answerButton1 = (RadioButton) findViewById(R.id.answerButton1);
        answerButton2 = (RadioButton) findViewById(R.id.answerButton2);
        answerButton3 = (RadioButton) findViewById(R.id.answerButton3);
        answerButton4 = (RadioButton) findViewById(R.id.answerButton4);
        answerGroup = (RadioGroup) findViewById(R.id.answerGroup);
        skipButton = (Button) findViewById(R.id.skipButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        skipButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

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

        int userSelection = answerGroup.getCheckedRadioButtonId();
        if (userSelection == answerButton1.getId()) {
            userAnswer = answerButton1.getText().toString();
        } else if (userSelection == answerButton2.getId()) {
            userAnswer = answerButton2.getText().toString();
        } else if (userSelection == answerButton3.getId()) {
            userAnswer = answerButton3.getText().toString();
        } else if (userSelection == answerButton4.getId()) {
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
        answerGroup.clearCheck();
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

        if (v.getId() == R.id.submitButton) {
            questionLogic();
        } else if (v.getId() == R.id.skipButton) {
            questionLogic();
        }

        updateUI();
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
}
