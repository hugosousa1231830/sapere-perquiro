package tutorials.databases.repositories;

import tutorials.databases.repositories.datamodels.publisher.BookDataModel;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository<T>{
    T save(T book);
    Optional<T> findById (String id);
    void deleteById(String authorId);
    Collection<T> findBooksByAuthorId(String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
