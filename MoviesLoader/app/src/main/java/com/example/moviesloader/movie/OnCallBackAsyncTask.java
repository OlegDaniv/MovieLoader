package com.example.moviesloader.movie;

import java.util.ArrayList;

public interface OnCallBackAsyncTask {

    void onDownloadStarted();

    void onDownloadFinished(ArrayList<Movie> result);
}
