package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

@Profile("jpa")
public interface JpaBookRepository extends JpaRepository<Book, String>, BookRepository {
   Collection<Book> findBooksByAuthorId (String authorId);
   boolean deleteAllByAuthorId(String authorId);
}
