package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.datamodels.book.PublisherJPADataModel;

@Profile("jpa")
public interface JpaPublisherRepository extends JpaRepository<PublisherJPADataModel, String>, PublisherRepository<PublisherJPADataModel> {
}
