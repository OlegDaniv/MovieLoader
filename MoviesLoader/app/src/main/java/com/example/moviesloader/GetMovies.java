package com.example.moviesloader;

import android.os.AsyncTask;

public class GetMovies extends AsyncTask<Void, String, String> {

    public CallBackAsyncTask delegate;

    public GetMovies(CallBackAsyncTask delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.processCallBack("Wait...");
    }

    @Override
    protected String doInBackground(Void... voids) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return "DONE";
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinished(s);
    }
}
