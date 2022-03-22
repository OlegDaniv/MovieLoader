package com.example.moviesloader.writer;

public interface WhoWroteItCallBack {
   void postExecute(String titleText, String authorText);
   void postNull();

}
