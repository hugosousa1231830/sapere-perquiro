package tutorials.databases.repositories.cassandra;

import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.datamodels.author.AuthorCassandraDataModel;

@Profile("cassandra")
public interface CassandraAuthorRepository extends CassandraRepository<AuthorCassandraDataModel, String>, AuthorRepository<AuthorCassandraDataModel> {
}
