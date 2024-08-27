package tutorials.databases.service;

import org.springframework.stereotype.Service;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher addPublisher(String name, String address) {
        Publisher publisher = Publisher.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();
        return publisherRepository.save(publisher);
    }

    public Optional<Publisher> findById(String id) {
        return publisherRepository.findById(id);
    }

    public void deletePublisher(String id) {
        publisherRepository.deleteById(id);
    }
}
