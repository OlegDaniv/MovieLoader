package com.example.moviesloader;

import android.content.Context;
import java.util.ArrayList;

public class Movie {

    public static ArrayList<Movie> movies = new ArrayList<>();
    private final String name;
    private final String description;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ArrayList<Movie> generateMovies(Context context) {
        String[] name = context.getResources().getStringArray(R.array.name);
        String[] description = context.getResources().getStringArray(R.array.description);
        int minArray = Math.min(name.length, description.length);
//        ArrayList<Movie> movies = new ArrayList<>(minArray);
        for (int i = 0; i < minArray; i++) {
            movies.add(new Movie(name[i], description[i]));
        }
        return movies;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
