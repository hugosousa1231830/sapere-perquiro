package tutorials.databases.repositories.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.datamodels.author.AuthorRedisDataModel;

@Profile("redis")
public interface RedisAuthorRepository extends CrudRepository<AuthorRedisDataModel, String>, AuthorRepository<AuthorRedisDataModel> {
}
