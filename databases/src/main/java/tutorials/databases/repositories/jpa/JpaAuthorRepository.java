package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.AuthorRepository;

@Profile("jpa")
public interface JpaAuthorRepository extends JpaRepository<AuthorDataModel, String>, AuthorRepository {
}
