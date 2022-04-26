package com.example.moviesloader.movie;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MoviesListTest {


    String[] names = {"name one", "name two", "name three", "name four"};
    String[] descriptions = {"description one", "name two", "name three", "name four"};

    @Test
    public void returnExpectedSize() {
        MoviesList moviesList = new MoviesList();
        ArrayList<Movie> generateMovies = moviesList.generateMovies(names,descriptions);
        assertEquals(4, generateMovies.size());
    }
}