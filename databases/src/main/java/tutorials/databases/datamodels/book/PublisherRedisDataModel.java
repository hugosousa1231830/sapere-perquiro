package tutorials.databases.datamodels.book;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@RedisHash("publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRedisDataModel implements PublisherDataModel {

    @Id
    private String id;

    private String name;

    private String address;
}
