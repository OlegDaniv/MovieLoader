package com.example.moviesloader.writer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void booksAreEqual() {
        String expectedTitle = "Some book";
        String expectedAuthors = "Some authors";
        Book book = new Book(expectedTitle, expectedAuthors);
        Book book1 = new Book("Some book", "Some authors");
        assertEquals(book, book1);
    }
}