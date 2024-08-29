package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.AuthorRepository;

@Profile("mongo")
public interface MongoAuthorRepository extends MongoRepository<AuthorDataModel, String>, AuthorRepository {
}