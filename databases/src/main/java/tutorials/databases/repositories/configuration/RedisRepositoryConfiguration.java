package tutorials.databases.repositories.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.redis.RedisAuthorRepository;
import tutorials.databases.repositories.redis.RedisBookRepository;
import tutorials.databases.repositories.redis.RedisPublisherRepository;

@Configuration
@Profile("redis")
@EnableRedisRepositories(basePackages = "tutorials.databases.repositories.redis")
public class RedisRepositoryConfiguration {

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }

    @Bean
    public AuthorRepository redisAuthorRepository(RedisAuthorRepository redisAuthorRepository) {
        return redisAuthorRepository;
    }

    @Bean
    public BookRepository redisBookRepository(RedisBookRepository redisBookRepository) {
        return redisBookRepository;
    }

    @Bean
    public PublisherRepository redisPublisherRepository(RedisPublisherRepository redisPublisherRepository) {
        return redisPublisherRepository;
    }

}