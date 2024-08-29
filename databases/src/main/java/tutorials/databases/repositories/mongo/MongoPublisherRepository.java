package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.PublisherRepository;

@Profile("mongo")
public interface MongoPublisherRepository extends MongoRepository<PublisherDataModel, String>, PublisherRepository {
}