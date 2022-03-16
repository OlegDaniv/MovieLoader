package com.example.moviesloader;

import java.util.ArrayList;

public class Movie {

    private final String name;
    private final String description;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ArrayList<Movie> generateMovies() {
        int minArray = Math.min(MainActivity.names.length, MainActivity.descriptions.length);
        ArrayList<Movie> movies = new ArrayList<>(minArray);
        for (int i = 0; i < minArray; i++) {
            movies.add(new Movie(MainActivity.names[i], MainActivity.descriptions[i]));
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
