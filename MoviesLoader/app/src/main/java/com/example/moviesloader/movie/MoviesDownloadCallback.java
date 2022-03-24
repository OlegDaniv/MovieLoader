package com.example.moviesloader.movie;

import java.util.ArrayList;

public interface MoviesDownloadCallback {

    void onDownloadStarted();

    void onDownloadFinished(ArrayList<Movie> result);
}
