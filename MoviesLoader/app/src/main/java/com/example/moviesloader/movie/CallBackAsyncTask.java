package com.example.moviesloader.movie;

import java.util.ArrayList;

public interface CallBackAsyncTask {
    void downloadStart();
    void downloadFinished(ArrayList<Movie> result);
}
