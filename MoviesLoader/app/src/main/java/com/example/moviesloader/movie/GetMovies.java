package com.example.moviesloader.movie;

import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;

public class GetMovies extends AsyncTask<String[], Void, ArrayList<Movie>> {

    private static final String TAG = GetMovies.class.getSimpleName();
    private MoviesDownloadCallback delegate;

    public GetMovies(MoviesDownloadCallback delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.onDownloadStarted();
    }

    @Override
    protected ArrayList<Movie> doInBackground(String[]... parameters) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Log.e(TAG, "doInBackground", e);
        }
        return new MoviesList().generateMovies(parameters[0], parameters[1]);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.onDownloadFinished(result);
    }
}

