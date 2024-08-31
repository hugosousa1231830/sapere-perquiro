package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.datamodels.publisher.BookMongoDataModel;

import java.util.Collection;

@Profile("mongo")
public interface MongoBookRepository extends MongoRepository<BookMongoDataModel, String>, BookRepository<BookMongoDataModel> {
    Collection<BookMongoDataModel> findBooksByAuthorId (String authorId);
    boolean deleteAllByAuthorId(String authorId);
}