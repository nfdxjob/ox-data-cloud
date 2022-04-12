package org.dshubs.odc.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author create by wangxian 2022/4/12
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.data.redis.connection.RedisConnectionFactory")
public class OdcRedisAutoConfiguration {

    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        this.buildTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        this.buildTemplate(stringRedisTemplate, redisConnectionFactory);
        return stringRedisTemplate;
    }

    private void buildTemplate(RedisTemplate<String, String> redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setStringSerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

    @Bean
    public ValueOperations<String, String> valueOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public HashOperations<String, String, String> hashOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ListOperations<String, String> listOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, String> setOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> zSetOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
