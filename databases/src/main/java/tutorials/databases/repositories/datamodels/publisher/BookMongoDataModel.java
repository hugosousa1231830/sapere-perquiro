package tutorials.databases.repositories.datamodels.publisher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMongoDataModel implements BookDataModel {

    @Id
    private String id;

    private String title;

    private String genre;

    private String authorId;

    private String publisherId;
}
