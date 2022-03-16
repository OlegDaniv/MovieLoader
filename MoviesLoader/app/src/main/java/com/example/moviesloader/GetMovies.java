package com.example.moviesloader;

import android.os.AsyncTask;
import java.util.ArrayList;

public class GetMovies extends AsyncTask<Movie, Movie, ArrayList<Movie>> {

    public CallBackAsyncTask delegate;

    public GetMovies(CallBackAsyncTask delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.downloadStart();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Movie... passing) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : passing) {
            result.add(movie);
            publishProgress(movie);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Movie... movies) {
        delegate.downloadOverview(movies[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        delegate.downloadFinished();
    }
}
