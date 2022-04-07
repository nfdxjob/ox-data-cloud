package org.dshubs.odc.core.cache;

import org.springframework.stereotype.Component;

/**
 * @author create by wangxian 2022/4/7
 */
@Component
public class RedisCache<K, V> implements OxCache<K, V> {
    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public void remove(K key) {

    }
}
