package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

public interface RedisBookRepository extends CrudRepository<Book, String>, BookRepository {
}
