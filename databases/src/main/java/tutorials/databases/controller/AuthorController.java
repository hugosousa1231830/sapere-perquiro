package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Author;
import tutorials.databases.service.AuthorService;

import java.util.Optional;

@RestController
@RequestMapping("/authors") // Base URL for all methods in this controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestParam String name, @RequestParam String address) {
        Author author = authorService.addAuthor(name, address);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable String authorId) {
        Optional<Author> author = authorService.findById(authorId);
        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteByAuthorId(@PathVariable String authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }
}
