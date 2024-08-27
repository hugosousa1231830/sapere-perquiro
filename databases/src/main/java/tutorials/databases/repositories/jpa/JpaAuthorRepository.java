package tutorials.databases.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

@Repository
public interface JpaAuthorRepository extends JpaRepository<Author, String>, AuthorRepository {
}
