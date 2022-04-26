package com.example.moviesloader.writer;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

public class FetchBook extends AsyncTask<String, Void, String> {

    public final String TAG = this.getClass().getName();
    private BookResultCallBack bookResultCallBack;
    private NetworkUtils networkUtils;
    private final String  JSON_ARRAY= "item";
    private final String  JSON_OBJECT= "volumeInfo";
    private final String BOOK_TITLE = "title";
    private final String BOOK_AUTHOR = "authors";
    private final String BOOK_AUTHOR_IS_UNKNOWN = "The author is unknown";

    public FetchBook() {
    }

    FetchBook(BookResultCallBack onWhoWroteItCallBack) {
        this.bookResultCallBack = onWhoWroteItCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String bookJSONString = null;
        try {
            bookJSONString = new NetworkUtils().getBookInfo(strings[0]);
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
        }
        return bookJSONString;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            bookResultCallBack.onExecute(generateBooks(s));
        } catch (JSONException e) {
            Log.e(TAG, "onPostExecute: ", e);
            bookResultCallBack.onResultsNotFound();
        }
    }

    private ArrayList<Book> generateBooks(String s) throws JSONException {
        JSONObject jsonObject = new JSONObject(s);
        JSONArray itemsArray = jsonObject.getJSONArray(JSON_ARRAY);
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < NetworkUtils.NUMBER_OF_RESULTS; i++) {
            JSONObject book = itemsArray.getJSONObject(i);
            JSONObject volumeInfo = book.getJSONObject(JSON_OBJECT);
            books.add(resolveBook(volumeInfo.optString(BOOK_TITLE),
                    volumeInfo.optString(BOOK_AUTHOR, null)));
        }
        return books;
    }

    protected Book resolveBook(String title, String author) {
        if (author == null) {
            return new Book(title, BOOK_AUTHOR_IS_UNKNOWN);
        } else {
            return new Book(title, author);
        }
    }
}