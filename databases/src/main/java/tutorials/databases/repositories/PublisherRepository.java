package tutorials.databases.repositories;

import tutorials.databases.domain.Publisher;

import java.util.Optional;

public interface PublisherRepository{
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(String id);
    void deleteById(String authorId);
}
