package tutorials.databases.datamodels.author;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorMongoDataModel implements AuthorDataModel {

    @Id
    private String id;
    private String name;
    private String address;
}
