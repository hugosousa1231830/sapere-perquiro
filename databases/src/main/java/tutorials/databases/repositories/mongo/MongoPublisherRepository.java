package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.datamodels.book.PublisherMongoDataModel;

@Profile("mongo")
public interface MongoPublisherRepository extends MongoRepository<PublisherMongoDataModel, String>, PublisherRepository<PublisherMongoDataModel> {
}