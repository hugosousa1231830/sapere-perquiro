package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface CassandraAuthorRepository extends CassandraRepository<Author, String>, AuthorRepository {


}
