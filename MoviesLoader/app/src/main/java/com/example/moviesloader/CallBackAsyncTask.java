package com.example.moviesloader;

public interface CallBackAsyncTask {
    void downloadStart();
    void downloadFinished();
    void downloadOverview(Movie movie);

    
}
