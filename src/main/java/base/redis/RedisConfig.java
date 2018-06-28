package base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;


@Configuration
public class RedisConfig {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 6379;

    @Bean(name = "jedis")
    public Jedis getJedis() {
        return new Jedis(HOST, PORT);
    }
}
