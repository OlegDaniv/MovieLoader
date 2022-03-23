package com.example.moviesloader.writer;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class FetchBook extends AsyncTask<String, Void, String> {

    private final OnWhoWroteItCallBack onWhoWroteItCallBack;

    FetchBook(OnWhoWroteItCallBack onWhoWroteItCallBack) {
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
            ArrayList<Book> books = new ArrayList<>(1);
            for (int i = 0; i < 1; i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                books.add(new Book(volumeInfo.getString("title"), volumeInfo.getString("authors")));
            }
            onWhoWroteItCallBack.onExecute(books);
        } catch (Exception e) {
            onWhoWroteItCallBack.onNotFoundResults();
            e.printStackTrace();
        }
    }
}