package tutorials.databases.repositories.cassandra;

import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.datamodels.publisher.BookCassandraDataModel;

import java.util.Collection;

@Profile("cassandra")
public interface CassandraBookRepository extends CassandraRepository<BookCassandraDataModel, String>, BookRepository<BookCassandraDataModel> {
    Collection<BookCassandraDataModel> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
