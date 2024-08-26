package graphql.graphqltutorial;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorService {
    Map<String, Author> authors = new HashMap<>();

    public Author createAuthor(String name, String address) {
        Author newAuthor = Author.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();
        authors.put(newAuthor.getId(), newAuthor);
        return newAuthor;
    }

    Author findById(String id) {
        return authors.get(id);
    }
}