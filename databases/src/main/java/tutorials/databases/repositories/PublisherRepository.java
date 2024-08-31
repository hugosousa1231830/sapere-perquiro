package tutorials.databases.repositories;

import tutorials.databases.repositories.datamodels.book.PublisherDataModel;

import java.util.Optional;

public interface PublisherRepository<T>{
    T save(T publisher);
    Optional<T> findById(String id);
    void deleteById(String authorId);
}
