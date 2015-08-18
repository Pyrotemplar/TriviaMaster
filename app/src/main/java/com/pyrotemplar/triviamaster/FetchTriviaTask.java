package com.pyrotemplar.triviamaster;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.pyrotemplar.triviamaster.Database.QuestionDAO;
import com.pyrotemplar.triviamaster.Objects.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pyrotemplar on 8/10/2015.
 */
public class FetchTriviaTask extends AsyncTask<String, Void, ArrayList<Question>> {
    private final String LOG_TAG = FetchTriviaTask.class.getSimpleName();
    private QuestionDAO questionDAO;
    private Context context;
    private String URL;

    public FetchTriviaTask(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        URL = params[0];
        final String CATEGORY_ID = params[1];
        final String LIMIT = params[2];
        final String PAGE = params[3];

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON  response as a string.
        String JsonStrResponse = null;

        URL url = null;
        try {

            Uri builtUri = Uri.parse(URL).buildUpon()
                    .appendQueryParameter("categoryId", CATEGORY_ID)
                    .appendQueryParameter("limit", LIMIT)
                    .appendQueryParameter("page", PAGE)
                    .build();

            url = new URL(builtUri.toString());


            Log.d(LOG_TAG, "Built URI " + URL);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Mashape-Key", context.getResources().getString(R.string.API_KEY_TESTING));
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            JsonStrResponse = buffer.toString();

            if (buffer.length() == 0) {
                // Stream was empty.
                return null;
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getTriviaDataFromJson(JsonStrResponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> result) {

        if (result != null) {
            questionDAO = new QuestionDAO(context);
            questionDAO.open();

            for (Question q : result) {
                questionDAO.AddQuestion(q);
            }
        }
    }

    private ArrayList<Question> getTriviaDataFromJson(String jsonStrResponse) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonStrResponse);
        ArrayList<Question> listOfQuestion = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Question question = new Question();
                question.setQuestionID(jsonObject.getString("id"));
                question.setQuestionText(jsonObject.getString("q_text"));
                question.setQuestionOption1(jsonObject.getString("q_options_1"));
                question.setQuestionOption2(jsonObject.getString("q_options_2"));
                question.setQuestionOption3(jsonObject.getString("q_options_3"));
                question.setQuestionOption4(jsonObject.getString("q_options_4"));
                question.setQuestionAnswer(jsonObject.getString("q_correct_option"));
                question.setCategoryID(jsonObject.getString("categ_id"));
                listOfQuestion.add(question);
            } catch (JSONException e) {
                Log.d(LOG_TAG, "Error parsing JsonArray", e);
            }
        }

        return listOfQuestion;
    }
}


