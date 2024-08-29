package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface RedisPublisherRepository extends CrudRepository<Publisher, String>, PublisherRepository {
}
