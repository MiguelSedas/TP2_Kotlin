package Exer1_3

class Pipeline{

    /**
     * Variavel: stages - guarda uma lista de transformações
     */
    private val stages = mutableListOf< Pair<String, (List<String>) -> List<String>>>()

    /**Função que adiciona um nome a um pipeline
     * Parametro: name - é usado para descrever o pipeline
     * Parametro: Transform - é uma função lambda que recebe uma list e retorna o mesmo tipo
     */
    fun addStage(name : String, transform: (List<String>) -> List<String>) {
        stages.add(name to transform)
    }
}