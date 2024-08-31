package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.datamodels.author.AuthorMongoDataModel;

@Profile("mongo")
public interface MongoAuthorRepository extends MongoRepository<AuthorMongoDataModel, String>, AuthorRepository<AuthorMongoDataModel> {
}