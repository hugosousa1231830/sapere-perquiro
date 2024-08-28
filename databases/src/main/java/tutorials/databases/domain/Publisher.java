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
@Table("publisher") // Cassandra
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id // JPA
    @PrimaryKey // Cassandra
    private String id;

    @Column("name") // Cassandra
    private String name;

    @Column("address") // Cassandra
    private String address;
}
