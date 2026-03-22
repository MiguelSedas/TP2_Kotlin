package Exer1_2

/**
 * Cache genérica em memória que mapeia chaves do tipo [K] para valores do tipo [V].
 * Suporta operações básicas de inserção, consulta, remoção e transformação de entradas.
 *
 * @param K tipo da chave — não pode ser null
 * @param V tipo do valor — não pode ser null
 */
class Cache<K : Any, V : Any> {

    /** Mapa interno mutável que armazena as entradas da cache */
    private val map = mutableMapOf<K, V>()

    /**
     * Insere ou substitui uma entrada na cache.
     * Se a chave já existir, o valor anterior é sobrescrito.
     * @param key chave da entrada
     * @param value valor a associar à chave
     */
    fun put(key: K, value: V) {
        map[key] = value
    }

    /**
     * Consulta o valor associado a uma chave.
     * @param key chave a procurar
     * @return o valor associado à chave, ou null se a chave não existir
     */
    fun get(key: K): V? {
        return map[key]
    }

    /**
     * Remove uma entrada da cache pela sua chave.
     * Se a chave não existir, não faz nada.
     * @param key chave da entrada a remover
     */
    fun evict(key: K) {
        map.remove(key)
    }

    /**
     * Devolve o número de entradas atualmente armazenadas na cache.
     * @return número de entradas como Int
     */
    fun size(): Int {
        return map.size
    }

    /**
     * Devolve o valor associado à chave se esta existir.
     * Caso contrário, calcula um valor por omissão através da lambda [defaultValue],
     * insere-o na cache e devolve-o.
     * @param key chave a procurar ou inserir
     * @param defaultValue lambda sem parâmetros que calcula o valor por omissão
     * @return o valor existente ou o novo valor calculado
     */
    fun getOrPut(key: K, defaultValue: () -> V): V {
        return map.getOrPut(key, defaultValue)
    }

    /**
     * Aplica uma transformação ao valor associado a uma chave, se esta existir.
     * O valor atual é substituído pelo resultado da [action].
     * @param key chave da entrada a transformar
     * @param action lambda que recebe o valor atual e devolve o novo valor
     * @return true se a chave existia e a transformação foi aplicada, false caso contrário
     */
    fun transform(key: K, action: (V) -> V): Boolean {
        if (map.containsKey(key)) {
            map[key] = action(map[key]!!)
            return true
        }
        return false
    }

    /**
     * Devolve uma cópia imutável do estado atual da cache.
     * O chamador não consegue modificar a cache através do mapa devolvido.
     * @return Map<K, V> imutável com todas as entradas atuais
     */
    fun snapshot(): Map<K, V> {
        return map.toMap()
    }

    /**
     * Filtra as entradas da cache cujos valores satisfazem o predicado dado.
     * Não modifica a cache original.
     * @param predicate lambda que recebe um valor e devolve true se deve ser incluído
     * @return Map<K, V> imutável apenas com as entradas que satisfazem o predicado
     */
    fun filterValues(predicate: (V) -> Boolean): Map<K, V> {
        return map.filterValues { predicate(it) }.toMap()
    }
}