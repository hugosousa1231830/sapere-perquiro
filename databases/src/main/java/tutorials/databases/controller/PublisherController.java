package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Publisher;
import tutorials.databases.service.PublisherService;

import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Publisher> addPublisher(@RequestParam String name, @RequestParam String address) {
        Publisher publisher = publisherService.addPublisher(name, address);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable String id) {
        Optional<Publisher> publisher = publisherService.findById(id);
        return publisher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable String id) {
       try {
           publisherService.deletePublisher(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
}