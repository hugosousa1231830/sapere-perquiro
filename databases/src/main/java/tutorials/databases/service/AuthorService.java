package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GeneralMapper generalMapper;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, GeneralMapper generalMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.generalMapper = generalMapper;
    }

    public Author addAuthor(String name, String address) {
        Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();

        AuthorDataModel authorDataModel = generalMapper.toAuthorDataModel(author);
        AuthorDataModel savedAuthorDataModel = (AuthorDataModel) authorRepository.save(authorDataModel);
        return generalMapper.toAuthor(savedAuthorDataModel);
    }

    public Optional<Author> findById(String authorId) {
        Optional<AuthorDataModel> authorDataModel = authorRepository.findById(authorId);
        return authorDataModel.map(generalMapper::toAuthor);
    }

    @Transactional
    public void deleteAuthor(String id) {
        bookRepository.deleteAllByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
