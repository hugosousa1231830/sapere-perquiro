package tutorials.databases.repositories.datamodels.author;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorJPADataModel implements AuthorDataModel {

    @Id
    private String id;
    private String name;
    private String address;
}
