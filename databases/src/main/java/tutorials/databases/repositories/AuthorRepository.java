package tutorials.databases.repositories;

import tutorials.databases.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(String id);
    void deleteById(String id);
}
