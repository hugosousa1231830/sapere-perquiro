package tutorials.databases.domain;

import jakarta.persistence.Entity; // JPA
import jakarta.persistence.Id; // JPA
import org.springframework.data.cassandra.core.mapping.Column; // Cassandra
import org.springframework.data.cassandra.core.mapping.PrimaryKey; // Cassandra
import org.springframework.data.cassandra.core.mapping.Table; // Cassandra
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA
@Table("book") // Cassandra
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id // JPA
    @PrimaryKey // Cassandra
    private String id;

    @Column("title") // Cassandra
    private String title;

    @Column("genre") // Cassandra
    private String genre;

    @Column("author_id") // Cassandra
    private String authorId;

    @Column("publisher_id") // Cassandra
    private String publisherId;
}
