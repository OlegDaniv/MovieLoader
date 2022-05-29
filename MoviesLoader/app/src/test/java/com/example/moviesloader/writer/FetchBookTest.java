package com.example.moviesloader.writer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FetchBookTest {

    private final FetchBook fetchBook = new FetchBook();

    @Test
    public void resolveBookReturnExpectedResult() {
        String expectedTitle = "Romeo and Julieta";
        String expectedAuthors = "William Shakespeare";
        Book book = new Book(expectedTitle, expectedAuthors);
        Book bookResult = fetchBook.resolveBook(expectedTitle, expectedAuthors);
        assertEquals(book, bookResult);
    }
    
    @Test
    public void resolveBookReturnExpectedResultWhenAuthorsAreNull() {
        String expectedTitle = "Romeo and Julieta";
        String expectedAuthors = "The author is unknown";
        Book book = new Book(expectedTitle, expectedAuthors);
        Book bookResult = fetchBook.resolveBook(expectedTitle, null);
        assertEquals(book, bookResult);
    }

}