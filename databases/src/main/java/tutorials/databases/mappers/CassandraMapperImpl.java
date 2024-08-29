package tutorials.databases.mappers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tutorials.databases.datamodels.author.AuthorCassandraDataModel;
import tutorials.databases.datamodels.book.PublisherCassandraDataModel;
import tutorials.databases.datamodels.publisher.BookCassandraDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;
import tutorials.databases.datamodels.author.AuthorDataModel;
import tutorials.databases.datamodels.book.PublisherDataModel;
import tutorials.databases.datamodels.publisher.BookDataModel;

@Profile("cassandra")
@Component
public class CassandraMapperImpl implements GeneralMapper{

    public AuthorDataModel toAuthorDataModel(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorCassandraDataModel(
                author.getId(),
                author.getName(),
                author.getAddress()
        );
    }

    public Author toAuthor(AuthorDataModel authorDataModel) {
        if (authorDataModel == null) {
            return null;
        }
        return new Author(
                authorDataModel.getId(),
                authorDataModel.getName(),
                authorDataModel.getAddress()
        );
    }

    public PublisherDataModel toPublisherDataModel(Publisher publisher) {
        if (publisher == null) {
            return null;
        }
        return new PublisherCassandraDataModel(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress()
        );
    }

    public Publisher toPublisher(PublisherDataModel publisherDataModel) {
        if (publisherDataModel == null) {
            return null;
        }
        return new Publisher(
                publisherDataModel.getId(),
                publisherDataModel.getName(),
                publisherDataModel.getAddress()
        );
    }

    public BookDataModel toBookDataModel(Book book) {
        if (book == null) {
            return null;
        }
        return new BookCassandraDataModel(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getAuthorId(),
                book.getPublisherId()
        );
    }

    public Book toBook(BookDataModel bookDataModel) {
        if (bookDataModel == null) {
            return null;
        }
        return new Book(
                bookDataModel.getId(),
                bookDataModel.getTitle(),
                bookDataModel.getGenre(),
                bookDataModel.getAuthorId(),
                bookDataModel.getPublisherId()
        );
    }
}
