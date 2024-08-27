package tutorials.databases.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Author;

public interface JpaAuthorRepository extends JpaRepository<Author, String>, AuthorRepository {

}
