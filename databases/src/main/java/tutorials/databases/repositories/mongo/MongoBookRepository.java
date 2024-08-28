package tutorials.databases.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

public interface MongoBookRepository extends MongoRepository<Book, String>, BookRepository {
    Collection<Book> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}