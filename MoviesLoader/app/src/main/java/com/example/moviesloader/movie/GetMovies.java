package com.example.moviesloader.movie;

import android.os.AsyncTask;
import java.util.ArrayList;

public class GetMovies extends AsyncTask<Void, Void, ArrayList<Movie>> {

    private OnCallBackAsyncTask delegate;

    public GetMovies(OnCallBackAsyncTask delegate) {
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
            e.printStackTrace();
        }
        return Movie.generateMovies();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.onDownloadFinished(result);
    }
}
