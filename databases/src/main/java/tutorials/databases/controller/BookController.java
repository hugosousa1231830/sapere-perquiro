package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Book;
import tutorials.databases.service.BookService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestParam String title, @RequestParam String authorId) {
        Book book = bookService.addBook(title, authorId);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Collection<Book>> getBooksByAuthor(@PathVariable String authorId) {
        Collection<Book> books = bookService.findBooksByAuthorId(authorId);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity<Void> deleteBooksByAuthor(@PathVariable String authorId) {
        boolean deleted = bookService.deleteByAuthorId(authorId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
