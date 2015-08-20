package com.pyrotemplar.triviamaster.Activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pyrotemplar.triviamaster.FetchTriviaTask;
import com.pyrotemplar.triviamaster.Fragments.CategoryGridFragment;

import com.pyrotemplar.triviamaster.Fragments.NavigationDrawerFragment;
import com.pyrotemplar.triviamaster.R;

/**
 * Created by Pyrotemplar on 8/14/2015.
 */

public class HomeScreenActivity extends AppCompatActivity {
    private final String LOG_TAG = HomeScreenActivity.class.getSimpleName();


    Toolbar toolbar;
    CategoryGridFragment CategoryGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_appbar);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        CategoryGrid = (CategoryGridFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_category_grid);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        FetchTriviaTask test = new FetchTriviaTask(this);
        test.execute(getResources().getString(R.string.CategoryListURL), "26", "10", "1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Log.d(LOG_TAG, "Score " + data.getIntExtra("score", 0));
                Log.d(LOG_TAG, "numberOfQuestions " + data.getIntExtra("numberOfQuestions", 0));
                Intent intent = new Intent(this, FinalScoreActivity.class);
                intent.putParcelableArrayListExtra("finalQuestionItemsList", data.getParcelableArrayListExtra("finalQuestionItemsList"));
                intent.putExtra("score", data.getIntExtra("score", 0));
                intent.putExtra("numberOfQuestions", data.getIntExtra("numberOfQuestions", 0));
                Toast.makeText(HomeScreenActivity.this, "new intent from home to final score", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
