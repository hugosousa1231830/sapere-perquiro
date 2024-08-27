package tutorials.databases.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

@Repository
public interface MongoBookRepository extends MongoRepository<Book, String>, BookRepository {
    Collection<Book> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}