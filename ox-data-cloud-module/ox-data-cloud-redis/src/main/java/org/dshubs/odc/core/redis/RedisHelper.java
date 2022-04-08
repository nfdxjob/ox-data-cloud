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

    private final ValueOperations<String, String> valueOperation;

    private final HashOperations<String, String, String> hashOperation;

    private final ListOperations<String, String> listOperation;

    private final SetOperations<String, String> setOperation;

    private final ZSetOperations<String, String> zSetOperation;

    /**
     * 不过期
     */
    private static final long NOT_EXPIRED = -1L;

    public RedisHelper(RedisTemplate<String, String> redisTemplate,
                       ValueOperations<String, String> valueOperation,
                       HashOperations<String, String, String> hashOperation,
                       ListOperations<String, String> listOperation,
                       SetOperations<String, String> setOperation,
                       ZSetOperations<String, String> zSetOperation) {
        this.redisTemplate = redisTemplate;
        this.valueOperation = valueOperation;
        this.hashOperation = hashOperation;
        this.listOperation = listOperation;
        this.setOperation = setOperation;
        this.zSetOperation = zSetOperation;
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
        this.valueOperation.set(key, value);
    }

    public void strSet(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        this.valueOperation.set(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
    }

    public String strGet(final String key) {
        return this.valueOperation.get(key);
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
        return this.listOperation.leftPush(key, value);
    }

    public Long listLeftPush(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        Long result = this.listLeftPush(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
        return result;
    }

    public Long listLeftPushAll(final String key, final Collection<String> values) {
        return this.listOperation.leftPushAll(key, values);
    }


    public Long listRightPush(final String key, final String value) {
        return this.listOperation.rightPush(key, value);
    }

    public Long listRightPush(final String key, final String value, final long expire, final TimeUnit timeUnit) {
        Long result = this.listRightPush(key, value);
        if (expire != NOT_EXPIRED) {
            this.setExpire(key, expire, timeUnit);
        }
        return result;
    }

    public Long listRightPushAll(final String key, final Collection<String> values) {
        return this.listOperation.rightPushAll(key, values);
    }

    public List<String> listRange(final String key, final long start, final long end) {
        return this.listOperation.range(key, start, end);
    }

    public List<String> listAll(final String key) {
        return this.listOperation.range(key, 0, -1);
    }

    public String listLeftPop(final String key) {
        return this.listOperation.leftPop(key);
    }

    public String listRightPop(final String key) {
        return this.listOperation.rightPop(key);
    }

    public Long listLength(final String key) {
        return this.listOperation.size(key);
    }

    public void listSet(final String key, final long index, final String value) {
        this.listOperation.set(key, index, value);
    }

    public Long listDelete(final String key, final long index) {
        return this.listOperation.remove(key, index, index);
    }

    public String listIndex(final String key, final long index) {
        return this.listOperation.index(key, index);
    }

    public Long setAdd(final String key, final String value) {
        return this.setOperation.add(key, value);
    }

    public Long setAddAll(final String key, final String[] values) {
        return this.setOperation.add(key, values);
    }

    public Set<String> setMembers(final String key) {
        return this.setOperation.members(key);
    }

    public Boolean setIsMember(final String key, final String value) {
        return this.setOperation.isMember(key, value);
    }

    public Long setSize(final String key) {
        return this.setOperation.size(key);
    }

    public Set<String> setIntersect(final String key, final String otherKey) {
        return this.setOperation.intersect(key, otherKey);
    }

    public Set<String> setUnion(final String key, final String otherKey) {
        return this.setOperation.union(key, otherKey);
    }

    public Long setDelete(final String key, final String value) {
        return this.setOperation.remove(key, value);
    }

    public Long setDelete(final String key, final String... values) {
        return this.setOperation.remove(key, values);
    }

    public Set<String> setDifference(final String key, final String otherKey) {
        return this.setOperation.difference(key, otherKey);
    }

    public Long setDifferenceAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperation.differenceAndStore(key, otherKey, destKey);
    }

    public Long setUnionAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperation.unionAndStore(key, otherKey, destKey);
    }

    public Long setIntersectAndStore(final String key, final String otherKey, final String destKey) {
        return this.setOperation.intersectAndStore(key, otherKey, destKey);
    }

    public Boolean zSetAdd(final String key, final String value, final double score) {
        return this.zSetOperation.add(key, value, score);
    }

    public Double zSetScore(final String key, final String value) {
        return this.zSetOperation.score(key, value);
    }

    public Double zSetIncrementScore(final String key, final String value, final double delta) {
        return this.zSetOperation.incrementScore(key, value, delta);
    }

    public Long zSetRank(final String key, final String value) {
        return this.zSetOperation.rank(key, value);
    }

    public Long zSetReverseRank(final String key, final String value) {
        return this.zSetOperation.reverseRank(key, value);
    }

    public Long zSetSize(final String key) {
        return this.zSetOperation.size(key);
    }

    public Long zSetRemove(final String key, final String value) {
        return this.zSetOperation.remove(key, value);
    }

    public Long zSetRemoveByScore(final String key, final double start, final double end) {
        return this.zSetOperation.removeRangeByScore(key, start, end);
    }

    public Long zSetRemoveRange(final String key, final long start, final long end) {
        return this.zSetOperation.removeRange(key, start, end);
    }

    public Set<String> zSetRange(final String key, final long start, final long end) {
        return this.zSetOperation.range(key, start, end);
    }

    public Set<String> zSetReverseRange(final String key, final long start, final long end) {
        return this.zSetOperation.reverseRange(key, start, end);
    }

    public Set<String> zSetRangeByScore(final String key, final double min, final double max) {
        return this.zSetOperation.rangeByScore(key, min, max);
    }

    public Set<String> zSetReverseRangeByScore(final String key, final double min, final double max) {
        return this.zSetOperation.reverseRangeByScore(key, min, max);
    }

    public void hashPut(String key, String hashKey, String hashValue) {
        this.hashOperation.put(key, hashKey, hashValue);
    }

    public void hashPutAll(String key, Map<String, String> hashMap) {
        this.hashOperation.putAll(key, hashMap);
    }

    public String hashGet(String key, String hashKey) {
        return this.hashOperation.get(key, hashKey);
    }

    public List<String> hashMultiGet(String key, Collection<String> hashKeys) {
        return this.hashOperation.multiGet(key, hashKeys);
    }

    public Map<String, String> hashGetAll(String key) {
        return this.hashOperation.entries(key);
    }

    public void hashDelete(String key, String hashKey) {
        this.hashOperation.delete(key, hashKey);
    }

    public void hashDeleteAll(String key) {
        this.hashOperation.delete(key);
    }

    public Set<String> hashKeys(String key) {
        return this.hashOperation.keys(key);
    }


}
