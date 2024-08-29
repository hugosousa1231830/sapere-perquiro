package tutorials.databases.datamodels.author;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@RedisHash("authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRedisDataModel implements AuthorDataModel {

    @Id
    private String id;
    private String name;
    private String address;
}
