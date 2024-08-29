package tutorials.databases.repositories.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.mongo.MongoAuthorRepository;
import tutorials.databases.repositories.mongo.MongoBookRepository;
import tutorials.databases.repositories.mongo.MongoPublisherRepository;

@Configuration
@Profile("mongo")
@EnableMongoRepositories(basePackages = "tutorials.databases.repositories.mongo")
public class MongoRepositoryConfiguration extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mydb"; // Replace with your actual database name
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Bean
    public AuthorRepository mongoAuthorRepository(MongoAuthorRepository mongoAuthorRepository) {
        return mongoAuthorRepository;
    }

    @Bean
    public BookRepository mongoBookRepository(MongoBookRepository mongoBookRepository) {
        return mongoBookRepository;
    }

    @Bean
    public PublisherRepository mongoPublisherRepository(MongoPublisherRepository mongoPublisherRepository) {
        return mongoPublisherRepository;
    }
}
