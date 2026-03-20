package Exer1_2

class Cache<K : Any, V : Any> {
    private val map = mutableMapOf<K, V>()

    fun put(key: K, value: V) {
        map[key] = value
    }

    fun get(key: K): V? {
        return map[key]
    }

    fun evict(key: K) {
        map.remove(key)
    }

    fun size(): Int {
        return map.size
    }

    fun getOrPut(key: K, defaultValue: () -> V): V{
        return map.getOrPut(key, defaultValue)
    }

}