package com.example.moviesloader;

import android.os.AsyncTask;
import android.util.Log;

public class GetMovies extends AsyncTask<Void, Movie, Movie> {

    public CallBackAsyncTask delegate;

    public GetMovies(CallBackAsyncTask delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.processCallBack();
    }

    @Override
    protected Movie doInBackground(Void... voids) {
        for (Movie movie : Movie.movies) {
            publishProgress(movie);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Movie... movies) {
        delegate.addProcess(movies[0]);
    }

    @Override
    protected void onPostExecute(Movie movie) {
        delegate.processFinished();
    }
}
