package com.example.moviesloader;

import java.util.ArrayList;

public interface CallBackAsyncTask {
    void downloadStart();
    void downloadFinished(ArrayList<Movie> result);
}
