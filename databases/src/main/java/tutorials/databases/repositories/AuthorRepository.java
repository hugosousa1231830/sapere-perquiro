package tutorials.databases.repositories;

import tutorials.databases.datamodels.author.AuthorDataModel;

import java.util.Optional;

public interface AuthorRepository {
    AuthorDataModel save(AuthorDataModel author);
    Optional<AuthorDataModel> findById(String id);
    void deleteById(String id);
}
