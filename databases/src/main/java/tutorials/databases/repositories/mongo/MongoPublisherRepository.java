package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

@Profile("mongo")
public interface MongoPublisherRepository extends MongoRepository<Publisher, String>, PublisherRepository {
}