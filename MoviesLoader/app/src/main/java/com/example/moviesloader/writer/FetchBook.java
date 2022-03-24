package com.example.moviesloader.writer;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class FetchBook extends AsyncTask<String, Void, String> {

    public final String LOG_TAG = this.getClass().getName();
    private final int RESULT_NUMBER = Integer.parseInt(NetworkUtils.NUMBER_RESULTS);
    private BookResultCallBack onWhoWroteItCallBack;

    FetchBook(BookResultCallBack onWhoWroteItCallBack) {
        this.onWhoWroteItCallBack = onWhoWroteItCallBack;

    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            ArrayList<Book> books = new ArrayList<>();
            for (int i = 0; i < RESULT_NUMBER; i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                if (volumeInfo.isNull("authors")) {
                    books.add(new Book(volumeInfo.getString("title"), "The author is a stranger."));
                } else {
                    books.add(new Book(volumeInfo.getString("title"), volumeInfo.getString("authors")));
                }
            }
            onWhoWroteItCallBack.onExecute(books);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON Exception");
            onWhoWroteItCallBack.onNotFoundResults();
        }
    }
}
