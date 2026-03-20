package Exer1_2

class Cache<K : Any, V : Any> {
    private val map = mutableMapOf<K, V>()

    /**
     * Função para inserir uma entrada
     */
    fun put(key: K, value: V) {
        map[key] = value
    }

    /**
     * Função que caso a key exista, devolve o valor da respetiva Key
     * se não existir retorna null
     */
    fun get(key: K): V? {
        return map[key]
    }

    /**
     * Função que caso a key exista, remove a entrada da cache
     */
    fun evict(key: K) {
        map.remove(key)
    }

    /**
     * Função que retorna o número de entradas da cache
     * return: Int
     */
    fun size(): Int {
        return map.size
    }

    /**
     * Função que caso a key exista retorna o respetivo valor,
     * se não existir adiciona esse par <key, value> e retorna o valor
     * return: map<K, V>
     */
    fun getOrPut(key: K, defaultValue: () -> V): V{
        return map.getOrPut(key, defaultValue)
    }

    /**
     * Função que caso a key exista aplica a ação ao valor atual e retorna true,
     * se não existir retorna false
     * return: Boolean
     */
    fun transform(key: K, action: (V) -> V): Boolean {
        if (map.containsKey(key)) {
            map[key] = action(map[key]!!)
            return true
        }
        return false
    }

    /**
     * Função que retorna uma cópia do cache atual
     * Return: map<K, V> imutável
     */
    fun snapshot(): Map<K, V> {
        return map.toMap()
    }

    /**
     * Função que recebe uma condição sobre o valor e retorna um map imutável
     * Return: map<K, V> imutável
     */
    fun filterValues(predicate: (V) -> Boolean): Map<K, V> {
        val values = map.filterValues { predicate(it) }
        return values.toMap()
    }

}