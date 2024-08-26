package graphql.graphqltutorial;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<String, Book> books = new HashMap<>();

    public Book createBook(String title, String authorId) {
        Book newBook = Book.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .authorId(authorId)
                .build();

        books.put(newBook.getId(), newBook);

        return newBook;
    }

    public Collection<Book> findByAuthor(String authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
}
