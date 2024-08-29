package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.datamodels.publisher.BookDataModel;
import tutorials.databases.repositories.BookRepository;

public interface RedisBookRepository extends CrudRepository<BookDataModel, String>, BookRepository {
}
