package tutorials.databases.repositories;

import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository{
    Book save(Book book);
    Optional<Book> findById (String id);
    void deleteById(String authorId);
    Collection<Book> findBooksByAuthorId(String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
