package com.example.bookmanager;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.saveBook(book);
        assertNotNull(savedBook);
        assertEquals("Test Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testGetAllBooksEmptyList() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());

        List<Book> books = bookService.getAllBooks();
        assertTrue(books.isEmpty());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBooksWithData() {
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

        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }
}
