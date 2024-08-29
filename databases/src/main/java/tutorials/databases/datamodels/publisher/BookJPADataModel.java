package tutorials.databases.datamodels.publisher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookJPADataModel implements BookDataModel {

    @Id
    private String id;
    private String title;
    private String genre;
    private String authorId;
    private String publisherId;
}
