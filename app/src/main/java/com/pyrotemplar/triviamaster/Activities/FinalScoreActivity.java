package com.pyrotemplar.triviamaster.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.pyrotemplar.triviamaster.Adapters.FinalScoreListAdapter;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;

public class FinalScoreActivity extends AppCompatActivity {
    private final String LOG_TAG = FinalScoreActivity.class.getSimpleName();
    private RecyclerView questionListView;
    private FinalScoreListAdapter finalScoreListAdapter;
    ArrayList<FinalScoreItem> finalScoreItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Get ListView object from xml
        questionListView = (RecyclerView) findViewById(R.id.finalScoreQuestionList);

        finalScoreItemsList =  getIntent().getParcelableArrayListExtra("finalScoreItemsList");
        finalScoreListAdapter = new FinalScoreListAdapter(finalScoreItemsList);
        questionListView.setAdapter(finalScoreListAdapter);
        questionListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




        Log.d(LOG_TAG, "Score " + getIntent().getIntExtra("score", 0));
        Log.d(LOG_TAG, "numberOfQuestions " + getIntent().getIntExtra("numberOfQuestions",0));
        //getIntent().getStringExtra("score");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
