package tutorials.databases.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

@Repository
public interface JpaPublisherRepository extends JpaRepository<Publisher, String>, PublisherRepository {
}
