package org.dshubs.odc.core.cache;

/**
 * @author create by wangxian 2022/4/7
 */
public interface OxCache<K, V> {
    /**
     * Get the value for the given key.
     *
     * @param key key to retrieve
     * @return value for the given key
     */
    V get(K key);

    /**
     * Put the given value into the cache.
     *
     * @param key   key to store
     * @param value value to store
     */

    void put(K key, V value);

    /**
     * Remove the given key from the cache.
     *
     * @param key key to remove
     */
    void remove(K key);
}
