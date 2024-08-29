package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.PublisherRepository;

public interface RedisPublisherRepository extends CrudRepository<PublisherDataModel, String>, PublisherRepository {
}
