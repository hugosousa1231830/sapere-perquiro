package tutorials.databases.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Publisher;

public interface JpaPublisherRepository extends JpaRepository<Publisher, String>, PublisherRepository {
}
