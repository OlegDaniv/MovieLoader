package com.example.moviesloader.movie;

import org.junit.Assert;
import org.junit.Test;

public class MoviesListTest {

    @Test
    public void dummy() {
        MoviesList m = new MoviesList();
        m.generateMovies().add(new Movie("a","b"));
         Movie mov = new Movie("a","b");
        Assert.assertEquals(mov.getName(),m.generateMovies().get(0));
    }
}