package com.example.moviesloader.writer;

import java.util.List;

public interface BookResultCallBack {

    void onExecute(List<Book> books);

    void onResultsNotFound();

}
