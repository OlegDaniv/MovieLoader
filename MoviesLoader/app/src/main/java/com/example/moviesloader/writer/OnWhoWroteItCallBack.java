package com.example.moviesloader.writer;

import java.util.ArrayList;

public interface OnWhoWroteItCallBack {

    void onExecute(ArrayList<Book> books);

    void onNotFoundResults();

}
