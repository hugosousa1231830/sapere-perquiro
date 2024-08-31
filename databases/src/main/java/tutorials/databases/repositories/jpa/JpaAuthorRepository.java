package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.datamodels.author.AuthorJPADataModel;

@Profile("jpa")
public interface JpaAuthorRepository extends JpaRepository<AuthorJPADataModel, String>, AuthorRepository<AuthorJPADataModel> {
}
