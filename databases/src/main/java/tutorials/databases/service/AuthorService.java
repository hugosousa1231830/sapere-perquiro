package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author addAuthor(String name, String address) {
        Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();
        return authorRepository.save(author);
    }

    public Optional<Author> findById(String authorId) {
        return authorRepository.findById(authorId);
    }

    @Transactional
    public void deleteAuthor(String id) {
        bookRepository.deleteAllByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
