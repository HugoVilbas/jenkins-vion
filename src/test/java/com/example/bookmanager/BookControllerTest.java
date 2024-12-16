package com.example.bookmanager;

import com.example.bookmanager.controller.BookController;
import com.example.bookmanager.model.Book;
import com.example.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("Author");

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        Book createdBook = bookController.saveBook(book);

        assertNotNull(createdBook);
        assertEquals("New Book", createdBook.getTitle());
        verify(bookService, times(1)).saveBook(book);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> mockBooks = List.of(
                new Book() {{
                    setTitle("Book 1");
                    setAuthor("Author 1");
                }},
                new Book() {{
                    setTitle("Book 2");
                    setAuthor("Author 2");
                }}
        );

        when(bookService.getAllBooks()).thenReturn(mockBooks);

        List<Book> books = bookController.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        doNothing().when(bookService).deleteBook(bookId);

        assertDoesNotThrow(() -> bookController.deleteBook(bookId));
        verify(bookService, times(1)).deleteBook(bookId);
    }
}
