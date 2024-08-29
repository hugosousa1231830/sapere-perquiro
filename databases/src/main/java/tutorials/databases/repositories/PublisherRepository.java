package tutorials.databases.repositories;

import tutorials.databases.datamodels.book.PublisherDataModel;

import java.util.Optional;

public interface PublisherRepository{
    PublisherDataModel save(PublisherDataModel publisher);
    Optional<PublisherDataModel> findById(String id);
    void deleteById(String authorId);
}
