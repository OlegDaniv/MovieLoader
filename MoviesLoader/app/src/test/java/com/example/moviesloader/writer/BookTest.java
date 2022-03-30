package com.example.moviesloader.writer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void bookIsNotNull() {
        String title = "Some book";
        String authors = "Some author";
        Book book = new Book(title, authors);
        Assert.assertNotNull(book);
    }

    @Test
    public void booksIsEquals() {
        String title = "Some book";
        String authors = "Some author";
        Book book = new Book(title, authors);
        Book book1 = new Book("Some book", "Some author");
        assertEquals(book.title, book1.title);
        assertEquals(book.authors, book1.authors);
    }
}