package tutorials.databases.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, String>, BookRepository {
   Collection<Book> findBooksByAuthorId (String authorId);
   boolean deleteAllByAuthorId(String authorId);
}
