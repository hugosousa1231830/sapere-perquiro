package tutorials.databases.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

@Repository
public interface MongoPublisherRepository extends MongoRepository<Publisher, String>, PublisherRepository {
}