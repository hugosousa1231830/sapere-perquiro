package tutorials.databases.service;

import org.springframework.stereotype.Service;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(String title, String authorId) {
        Book book = Book.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .authorId(authorId)
                .build();
        return bookRepository.save(book);
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> findBooksByAuthorId(String authorId) {
        return bookRepository.findBooksByAuthorId(authorId);  // Assuming custom query method in BookRepository
    }


    public boolean deleteByAuthorId(String authorId) {
        return bookRepository.deleteAllByAuthorId(authorId);  // Assuming custom query method in BookRepository
    }
}
