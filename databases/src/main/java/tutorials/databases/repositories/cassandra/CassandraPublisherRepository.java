package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface CassandraPublisherRepository extends CassandraRepository<Publisher, String>, PublisherRepository {
}
