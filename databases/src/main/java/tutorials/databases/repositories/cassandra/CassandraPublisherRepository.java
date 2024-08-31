package tutorials.databases.repositories.cassandra;

import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.datamodels.book.PublisherCassandraDataModel;

@Profile("cassandra")
public interface CassandraPublisherRepository extends CassandraRepository<PublisherCassandraDataModel, String>, PublisherRepository<PublisherCassandraDataModel> {
}
