package tutorials.databases.datamodels.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherMongoDataModel implements PublisherDataModel {

    @Id
    private String id;

    private String name;

    private String address;
}
