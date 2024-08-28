package tutorials.databases.repositories;

import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Publisher;

import java.util.Optional;

public interface PublisherRepository{
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(String id);
    void deleteById(String authorId);
}
