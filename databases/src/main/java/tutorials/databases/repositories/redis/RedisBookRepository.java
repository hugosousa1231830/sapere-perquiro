package tutorials.databases.repositories.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.datamodels.publisher.BookRedisDataModel;

@Profile("redis")
public interface RedisBookRepository extends CrudRepository<BookRedisDataModel, String>, BookRepository<BookRedisDataModel> {
}
