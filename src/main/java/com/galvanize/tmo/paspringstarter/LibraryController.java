package com.galvanize.tmo.paspringstarter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class LibraryController {
    private int id = 1;
    private Books books = new Books();

    @GetMapping("/health")
    public void health() {

    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
            Book newBook = new Book(id++, book.getAuthor(), book.getTitle(), book.getYearPublished());
            books.addBook(newBook);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        }

    @GetMapping("/api/books")
    public Books getBooks() {
        List<Book> bookList = books.getBooks();
        bookList.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    @DeleteMapping("/api/books")
    public  ResponseEntity<Void> removeBooks() {
        books.clear();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}
