package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

@Profile("jpa")
public interface JpaPublisherRepository extends JpaRepository<Publisher, String>, PublisherRepository {
}
