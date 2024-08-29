package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface RedisAuthorRepository extends CrudRepository<Author, String>, AuthorRepository {
}
