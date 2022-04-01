package com.example.moviesloader.writer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class FetchBookTest {

    private final FetchBook fetchBook = new FetchBook();

    @Test
    public void checkBooksAuthors() {
        Book bookResult = fetchBook.CheckBooksAuthors("Romeo and Julieta", "William Shakespeare");
        assertEquals("Romeo and Julieta", bookResult.title);
        assertEquals("William Shakespeare", bookResult.authors);
    }

    @Test
    public void checkBooksAuthorsIsNull() {
        Book bookResult = fetchBook.CheckBooksAuthors("Romeo and Julieta", null);
        assertNotEquals(null, bookResult.title);
        assertNotEquals(null, bookResult.authors);
    }

    @Test
    public void correctBooksDF() {
        Book bookResult = fetchBook.CheckBooksAuthors("Romeo and Julieta", "William Shakespeare");
        assertNotEquals("ROMEO AND JULIETA", bookResult.title);
        assertNotEquals("WILLIAM SHAKESPEARE", bookResult.authors);
    }

    @Test
    public void bookResultIsNull() {
        Book bookResult = fetchBook.CheckBooksAuthors("Romeo and Julieta", "William Shakespeare");
        if (bookResult == null) {
            fail("fetchBook should not be null");
        }
    }
}