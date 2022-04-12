package org.dshubs.odc.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.core.util.JsonUtils;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作类
 *
 * @author create by wangxian 2022/4/7
 */
@Component
public class RedisHelper {
    private final RedisTemplate<String, String> redisTemplate;

    private final ValueOperations<String, String> valueOperations;

    private final HashOperations<String, String, String> hashOperations;

    private final ListOperations<String, String> listOperations;

    private final SetOperations<String, String> setOperations;

    private final ZSetOperations<String, String> zSetOperations;

    /**
     * 不过期
     */
    private static final long NOT_EXPIRED = -1L;

    public RedisHelper(RedisTemplate<String, String> redisTemplate,
                       ValueOperations<String, String> valueOperations,
                       HashOperations<String, String, String> hashOperations,
                       ListOperations<String, String> listOperations,
                       SetOperations<String, String> setOperations,
                       ZSetOperations<String, String> zSetOperations) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = valueOperations;
        this.hashOperations = hashOperations;
        this.listOperations = listOperations;
        this.setOperations = setOperations;
        this.zSetOperations = zSetOperations;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public Boolean setExpire(final String key, final long expire, final TimeUnit timeUnit) {
        return this.redisTemplate.expire(key, expire, timeUnit);
    }

    public Long getExpire(final String key) {
        return this.redisTemplate.getExpire(key);
    }

    public void strSet(final String key, final String value) {
        this.valueOperations.set(key, value);
    }

    public void strSet(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        this.valueOperations.set(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
    }

    public String strGet(final String key) {
        return this.valueOperations.get(key);
    }

    public String strGet(final String key, final long expire, final TimeUnit timeUnit) {
        String value = this.strGet(key);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
        return value;
    }

    public <T> T strGet(String key, Class<T> tClass) {
        String value = this.strGet(key);
        if (StringUtils.isNotBlank(value)) {
            return JsonUtils.getInstance().fromJson(value, tClass);
        }
        return null;

    }

    public Long listLeftPush(final String key, final String value) {
        return this.listOperations.leftPush(key, value);
    }

    public Long listLeftPush(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        Long result = this.listLeftPush(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
        return result;
    }

    public Long listLeftPushAll(final String key, final Collection<String> values) {
        return this.listOperations.leftPushAll(key, values);
    }


    public Long listRightPush(final String key, final String value) {
        return this.listOperations.rightPush(key, value);
    }

    public Long listRightPush(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        Long result = this.listRightPush(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
        return result;
    }

    public Long listRightPushAll(final String key, final Collection<String> values) {
        return this.listOperations.rightPushAll(key, values);
    }

    public List<String> listRange(final String key, final long start, final long end) {
        return this.listOperations.range(key, start, end);
    }

    public List<String> listAll(final String key) {
        return this.listOperations.range(key, 0, -1);
    }

    public String listLeftPop(final String key) {
        return this.listOperations.leftPop(key);
    }

    public String listRightPop(final String key) {
        return this.listOperations.rightPop(key);
    }

    public Long listLength(final String key) {
        return this.listOperations.size(key);
    }

    public void listSet(final String key, final long index, final String value) {
        this.listOperations.set(key, index, value);
    }

    public Long listDelete(final String key, final long index) {
        return this.listOperations.remove(key, index, index);
    }

    public String listIndex(final String key, final long index) {
        return this.listOperations.index(key, index);
    }

    public Long setAdd(final String key, final String value) {
        return this.setOperations.add(key, value);
    }

    public Long setAddAll(final String key, final String[] values) {
        return this.setOperations.add(key, values);
    }

    public Set<String> setMembers(final String key) {
        return this.setOperations.members(key);
    }

    public Boolean setIsMember(final String key, final String value) {
        return this.setOperations.isMember(key, value);
    }

    public Long setSize(final String key) {
        return this.setOperations.size(key);
    }

    public Set<String> setIntersect(final String key, final String otherKey) {
        return this.setOperations.intersect(key, otherKey);
    }

    public Set<String> setUnion(final String key, final String otherKey) {
        return this.setOperations.union(key, otherKey);
    }

    public Long setDelete(final String key, final String value) {
        return this.setOperations.remove(key, value);
    }

    public Long setDelete(final String key, final String... values) {
        return this.setOperations.remove(key, values);
    }

    public Set<String> setDifference(final String key, final String otherKey) {
        return this.setOperations.difference(key, otherKey);
    }

    public Long setDifferenceAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperations.differenceAndStore(key, otherKey, destKey);
    }

    public Long setUnionAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperations.unionAndStore(key, otherKey, destKey);
    }

    public Long setIntersectAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperations.intersectAndStore(key, otherKey, destKey);
    }

    public Boolean zSetAdd(final String key, final String value, final double score) {
        return this.zSetOperations.add(key, value, score);
    }

    public Double zSetScore(final String key, final String value) {
        return this.zSetOperations.score(key, value);
    }

    public Double zSetIncrementScore(final String key, final String value, final double delta) {
        return this.zSetOperations.incrementScore(key, value, delta);
    }

    public Long zSetRank(final String key, final String value) {
        return this.zSetOperations.rank(key, value);
    }

    public Long zSetReverseRank(final String key, final String value) {
        return this.zSetOperations.reverseRank(key, value);
    }

    public Long zSetSize(final String key) {
        return this.zSetOperations.size(key);
    }

    public Long zSetRemove(final String key, final String value) {
        return this.zSetOperations.remove(key, value);
    }

    public Long zSetRemoveByScore(final String key, final double start, final double end) {
        return this.zSetOperations.removeRangeByScore(key, start, end);
    }

    public Long zSetRemoveRange(final String key, final long start, final long end) {
        return this.zSetOperations.removeRange(key, start, end);
    }

    public Set<String> zSetRange(final String key, final long start, final long end) {
        return this.zSetOperations.range(key, start, end);
    }

    public Set<String> zSetReverseRange(final String key, final long start, final long end) {
        return this.zSetOperations.reverseRange(key, start, end);
    }

    public Set<String> zSetRangeByScore(final String key, final double min, final double max) {
        return this.zSetOperations.rangeByScore(key, min, max);
    }

    public Set<String> zSetReverseRangeByScore(final String key, final double min, final double max) {
        return this.zSetOperations.reverseRangeByScore(key, min, max);
    }

    public void hashPut(String key, String hashKey, String hashValue) {
        this.hashOperations.put(key, hashKey, hashValue);
    }

    public void hashPutAll(String key, Map<String, String> hashMap) {
        this.hashOperations.putAll(key, hashMap);
    }

    public String hashGet(String key, String hashKey) {
        return this.hashOperations.get(key, hashKey);
    }

    public List<String> hashMultiGet(String key, Collection<String> hashKeys) {
        return this.hashOperations.multiGet(key, hashKeys);
    }

    public Map<String, String> hashGetAll(String key) {
        return this.hashOperations.entries(key);
    }

    public void hashDelete(String key, String hashKey) {
        this.hashOperations.delete(key, hashKey);
    }

    public void hashDeleteAll(String key) {
        this.hashOperations.delete(key);
    }

    public Set<String> hashKeys(String key) {
        return this.hashOperations.keys(key);
    }


}
