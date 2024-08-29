//package tutorials.databases.repositories.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//import tutorials.databases.repositories.AuthorRepository;
//import tutorials.databases.repositories.BookRepository;
//import tutorials.databases.repositories.PublisherRepository;
//import tutorials.databases.repositories.cassandra.CassandraAuthorRepository;
//import tutorials.databases.repositories.cassandra.CassandraBookRepository;
//import tutorials.databases.repositories.cassandra.CassandraPublisherRepository;
//
//@Configuration
//@Profile("cassandra")
//@EnableCassandraRepositories(basePackages = "tutorials.databases.repositories.cassandra")
//public class CassandraRepositoryConfiguration extends AbstractCassandraConfiguration {
//
//    @Override
//    protected String getKeyspaceName() {
//        return "keyspace1"; // Replace with your actual keyspace name
//    }
//
//    @Bean
//    public AuthorRepository cassandraAuthorRepository(CassandraAuthorRepository cassandraAuthorRepository) {
//        return cassandraAuthorRepository;
//    }
//
//    @Bean
//    public BookRepository cassandraBookRepository(CassandraBookRepository cassandraBookRepository) {
//        return cassandraBookRepository;
//    }
//
//    @Bean
//    public PublisherRepository cassandraPublisherRepository(CassandraPublisherRepository cassandraPublisherRepository) {
//        return cassandraPublisherRepository;
//    }
//}