package tutorials.databases.repositories;

import java.util.Optional;

public interface AuthorRepository<T> {
    T save(T author);
    Optional<T> findById(String id);
    void deleteById(String id);
}
