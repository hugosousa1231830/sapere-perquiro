package tutorials.databases.repositories.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.jpa.JpaAuthorRepository;
import tutorials.databases.repositories.jpa.JpaBookRepository;
import tutorials.databases.repositories.jpa.JpaPublisherRepository;

@Configuration
@Profile("jpa")
@EnableJpaRepositories(basePackages = "tutorials.databases.repositories.jpa")
public class JpaRepositoryConfiguration {

    @Bean
    public AuthorRepository jpaAuthorRepository(JpaAuthorRepository jpaAuthorRepository) {
        return jpaAuthorRepository;
    }

    @Bean
    public BookRepository jpaBookRepository(JpaBookRepository jpaBookRepository) {
        return jpaBookRepository;
    }

    @Bean
    public PublisherRepository jpaPublisherRepository(JpaPublisherRepository jpaPublisherRepository) {
        return jpaPublisherRepository;
    }
}
