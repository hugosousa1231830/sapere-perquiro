package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.AuthorRepository;

public interface RedisAuthorRepository extends CrudRepository<AuthorDataModel, String>, AuthorRepository {
}
