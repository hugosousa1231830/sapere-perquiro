package tutorials.databases.repositories.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
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

    // Because we have Spring Data, it uses Lettuce instead of Jedis. Left here just for reference.

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//        jedisConFactory.setHostName("localhost");
//        jedisConFactory.setPort(6379);
//        return jedisConFactory;
//    }

//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("redis-db", 6379);
//
//        return new LettuceConnectionFactory(redisConfig);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(lettuceConnectionFactory());
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