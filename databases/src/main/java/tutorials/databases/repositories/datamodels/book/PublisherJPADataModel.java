package tutorials.databases.repositories.datamodels.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherJPADataModel implements PublisherDataModel {

    @Id
    private String id;
    private String name;
    private String address;
}
