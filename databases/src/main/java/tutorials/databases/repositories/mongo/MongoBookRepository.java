package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.datamodels.publisher.BookDataModel;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

@Profile("mongo")
public interface MongoBookRepository extends MongoRepository<BookDataModel, String>, BookRepository {
    Collection<BookDataModel> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}