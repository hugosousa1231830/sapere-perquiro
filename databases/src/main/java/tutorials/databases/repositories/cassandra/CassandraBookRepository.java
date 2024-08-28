package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

public interface CassandraBookRepository extends CassandraRepository<Book, String>, BookRepository {
    Collection<Book> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
