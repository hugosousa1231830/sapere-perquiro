package tutorials.databases.repositories;

import tutorials.databases.datamodels.publisher.BookDataModel;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository{
    BookDataModel save(BookDataModel book);
    Optional<BookDataModel> findById (String id);
    void deleteById(String authorId);
    Collection<BookDataModel> findBooksByAuthorId(String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
