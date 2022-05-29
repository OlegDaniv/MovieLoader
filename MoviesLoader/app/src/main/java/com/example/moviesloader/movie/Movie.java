package com.example.moviesloader.movie;

import androidx.annotation.Nullable;

public class Movie {

    private final String name;
    private final String description;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie movie = (Movie) obj;
        return name.equals(movie.name) && description.equals(movie.description);
    }
}

