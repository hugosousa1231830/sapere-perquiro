package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.datamodels.book.PublisherDataModel;
import tutorials.databases.domain.Publisher;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.PublisherRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final GeneralMapper generalMapper;

    public PublisherService(PublisherRepository publisherRepository, GeneralMapper generalMapper) {
        this.publisherRepository = publisherRepository;
        this.generalMapper = generalMapper;
    }

    // Adds a new publisher with the provided name and address
    public Publisher addPublisher(String name, String address) {
        Publisher publisher = Publisher.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();

        PublisherDataModel publisherDataModel = generalMapper.toPublisherDataModel(publisher);
        PublisherDataModel savedPublisherDataModel = publisherRepository.save(publisherDataModel);
        return generalMapper.toPublisher(savedPublisherDataModel);
    }

    // Finds a publisher by its ID
    public Optional<Publisher> findById(String id) {
        Optional<PublisherDataModel> publisherDataModel = publisherRepository.findById(id);
        return publisherDataModel.map(generalMapper::toPublisher);
    }

    // Deletes a publisher by its ID
    @Transactional
    public void deletePublisher(String id) {
        publisherRepository.deleteById(id);
    }
}
