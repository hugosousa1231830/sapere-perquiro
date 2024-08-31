package tutorials.databases.mappers;

import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;

public interface GeneralMapper {
    AuthorDataModel toAuthorDataModel(Author author);
    Author toAuthor(AuthorDataModel authorDataModel);

    PublisherDataModel toPublisherDataModel(Publisher publisher);
    Publisher toPublisher(PublisherDataModel publisherDataModel);

    BookDataModel toBookDataModel(Book book);
    Book toBook(BookDataModel bookDataModel);
}
