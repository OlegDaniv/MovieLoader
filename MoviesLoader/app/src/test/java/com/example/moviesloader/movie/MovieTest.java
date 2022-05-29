package com.example.moviesloader.movie;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void booksIsEquals() {
        String expectedTitle = "Some book";
        String expectedAuthors = "Some authors";
        Movie book = new Movie(expectedTitle, expectedAuthors);
        Movie book1 = new Movie("Some book", "Some authors");
        assertEquals(book, book1);
    }
}