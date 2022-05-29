package com.example.moviesloader.movie;

import java.util.ArrayList;

public class MoviesList {

    protected ArrayList<Movie> generateMovies(String[] names, String[] descriptions) {
        int minArray = Math.min(names.length, descriptions.length);
        ArrayList<Movie> movies = new ArrayList<>(minArray);
        for (int i = 0; i < minArray; i++) {
            movies.add(new Movie(names[i], descriptions[i]));
        }
        return movies;
    }

}
