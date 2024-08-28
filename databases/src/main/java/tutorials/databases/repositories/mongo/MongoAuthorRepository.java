package tutorials.databases.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface MongoAuthorRepository extends MongoRepository<Author, String>, AuthorRepository {
}