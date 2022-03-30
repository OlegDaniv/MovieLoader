package com.example.moviesloader.writer;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

public class FetchBook extends AsyncTask<String, Void, String> {

    private BookResultCallBack onWhoWroteItCallBack;

    FetchBook(BookResultCallBack onWhoWroteItCallBack) {
        this.onWhoWroteItCallBack = onWhoWroteItCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String bookJSONString = null;
        try {
            bookJSONString = new NetworkUtils().getBookInfo(strings[0]);
        } catch (IOException e) {
            Log.e("FetchBook", "doInBackground: ", e);
        }
        return bookJSONString;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            onWhoWroteItCallBack.onExecute(generateBooks(s));
        } catch (JSONException e) {
            Log.e("FetchBook", "onPostExecute: ", e);
            onWhoWroteItCallBack.onResultsNotFound();
        }
    }

    private ArrayList<Book> generateBooks(String s) throws JSONException {
        JSONObject jsonObject = new JSONObject(s);
        JSONArray itemsArray = jsonObject.getJSONArray("items");
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < NetworkUtils.NUMBER_OF_RESULTS; i++) {
            JSONObject book = itemsArray.getJSONObject(i);
            JSONObject volumeInfo = book.getJSONObject("volumeInfo");
            if (volumeInfo.isNull("authors")) {
                books.add(new Book(volumeInfo.getString("title"), "The author is unknown"));
            } else {
                books.add(new Book(volumeInfo.getString("title"), volumeInfo.getString("authors")));
            }
        }
        return books;
    }
}
