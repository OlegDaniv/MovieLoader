package com.example.moviesloader.movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieTest {

    @Test
    public void movieIsNotNull() {
        String description = "some description";
        String title = "Some movie";
        Movie movie = new Movie(title, description);
        Assert.assertNotNull(movie);
    }

    @Test
    public void movieIsEquals() {
        Movie movie = mock(Movie.class);
        when(movie.getName()).thenReturn("Some movie");
        when(movie.getDescription()).thenReturn("Some description");
        assertEquals("Some movie", movie.getName());
        assertEquals("Some description", movie.getDescription());
    }
}