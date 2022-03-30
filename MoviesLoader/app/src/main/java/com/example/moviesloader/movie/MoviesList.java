package com.example.moviesloader.movie;

import java.util.ArrayList;

public class MoviesList {

    protected ArrayList<Movie> generateMovies() {
        int minArray = Math.min(MovieLoaderFragment.names.length, MovieLoaderFragment.descriptions.length);
        ArrayList<Movie> movies = new ArrayList<>(minArray);
        for (int i = 0; i < minArray; i++) {
            movies.add(new Movie(MovieLoaderFragment.names[i], MovieLoaderFragment.descriptions[i]));
        }
        return movies;
    }

}
