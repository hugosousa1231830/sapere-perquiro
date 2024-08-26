package graphql.graphqltutorial;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class LibraryController {
    private final AuthorService authorService;
    private final BookService bookService;

    public LibraryController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @QueryMapping
    public Author authorById(@Argument String id) {
        return authorService.findById(id);
    }

    @MutationMapping
    public Author createAuthor(@Argument String name, @Argument String address) {
        return authorService.createAuthor(name, address);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument String authorId) {
        return bookService.createBook(title, authorId);
    }

    @SchemaMapping
    public Collection<Book> books(Author author) {
        return bookService.findByAuthor(author.getId());
    }
}