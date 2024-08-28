package tutorials.databases.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface JpaPublisherRepository extends JpaRepository<Publisher, String>, PublisherRepository {
}
