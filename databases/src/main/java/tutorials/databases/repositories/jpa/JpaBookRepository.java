package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.datamodels.publisher.BookJPADataModel;

import java.util.Collection;

@Profile("jpa")
public interface JpaBookRepository extends JpaRepository<BookJPADataModel, String>, BookRepository<BookJPADataModel> {
   Collection<BookJPADataModel> findBooksByAuthorId (String authorId);
   boolean deleteAllByAuthorId(String authorId);
}
