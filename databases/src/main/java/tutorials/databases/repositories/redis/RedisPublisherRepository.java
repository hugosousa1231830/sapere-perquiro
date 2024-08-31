package tutorials.databases.repositories.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.datamodels.book.PublisherRedisDataModel;

@Profile("redis")
public interface RedisPublisherRepository extends CrudRepository<PublisherRedisDataModel, String>, PublisherRepository<PublisherRedisDataModel> {
}
