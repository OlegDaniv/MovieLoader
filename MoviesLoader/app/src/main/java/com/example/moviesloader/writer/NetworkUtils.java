package com.example.moviesloader.writer;

import android.net.Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    protected static final int NUMBER_OF_RESULTS = 10;
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";
    private static final String BOOKS = "books";
    private static final String METHOD_REQUEST = "GET";

    protected String getBookInfo(String queryString) throws IOException {
        URL requestURL = requestURL(queryString);
        HttpURLConnection urlConnection = urlConnection(requestURL);
        InputStream inputStream = urlConnection.getInputStream();
        return stringBuilder(inputStream);
    }

    private String stringBuilder(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        return builder.toString();
    }

    private HttpURLConnection urlConnection(URL requestURL) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod(METHOD_REQUEST);
        urlConnection.connect();
        return urlConnection;
    }

    private URL requestURL(String queryParam) throws MalformedURLException {
        String builtURI = Uri.parse(NetworkUtils.BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryParam)
                .appendQueryParameter(MAX_RESULTS, Integer.toString(NetworkUtils.NUMBER_OF_RESULTS))
                .appendQueryParameter(PRINT_TYPE, NetworkUtils.BOOKS)
                .build().toString();
        return new URL(builtURI);
    }

}

