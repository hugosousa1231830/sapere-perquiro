package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.domain.Book;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GeneralMapper generalMapper;

    public BookService(BookRepository bookRepository, GeneralMapper generalMapper) {
        this.bookRepository = bookRepository;
        this.generalMapper = generalMapper;
    }

    public Book addBook(String title, String authorId) {
        Book book = Book.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .authorId(authorId)
                .build();

        BookDataModel bookDataModel = generalMapper.toBookDataModel(book);
        BookDataModel savedBookDataModel = (BookDataModel) bookRepository.save(bookDataModel);
        return generalMapper.toBook(savedBookDataModel);
    }

    public Optional<Book> findById(String id) {
        Optional<BookDataModel> bookDataModel = bookRepository.findById(id);
        return bookDataModel.map(generalMapper::toBook);
    }

    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> findBooksByAuthorId(String authorId) {
        Collection<BookDataModel> bookDataModels = bookRepository.findBooksByAuthorId(authorId);
        Collection<Book> books = new ArrayList<>();
        for (BookDataModel bookDataModel : bookDataModels) {
            books.add(generalMapper.toBook(bookDataModel));
        }
        return books;
    }

    @Transactional
    public boolean deleteByAuthorId(String authorId) {
        return bookRepository.deleteAllByAuthorId(authorId);
    }
}
