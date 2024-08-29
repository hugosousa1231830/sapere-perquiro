package tutorials.databases.datamodels.publisher;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCassandraDataModel implements BookDataModel {

    @PrimaryKey
    private String id;

    @Column("title")
    private String title;

    @Column("genre")
    private String genre;

    @Column("author_id")
    private String authorId;

    @Column("publisher_id")
    private String publisherId;
}
