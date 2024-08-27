package tutorials.databases.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

@Repository
public interface MongoAuthorRepository extends MongoRepository<Author, String>, AuthorRepository {
}