package tutorials.databases.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface MongoPublisherRepository extends MongoRepository<Publisher, String>, PublisherRepository {
}