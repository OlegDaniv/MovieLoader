package com.example.moviesloader.movie;

import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;

public class GetMovies extends AsyncTask<Void, Void, ArrayList<Movie>> {

    private MoviesDownloadCallback delegate;

    public GetMovies(MoviesDownloadCallback delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.onDownloadStarted();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Log.e("GetMovies", "doInBackground", e);
        }
        return new MoviesList().generateMovies();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.onDownloadFinished(result);
    }
}

