package tutorials.databases.repositories.datamodels.publisher;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@RedisHash("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRedisDataModel implements BookDataModel {

    @Id
    private String id;

    private String title;

    private String genre;

    private String authorId;

    private String publisherId;
}
