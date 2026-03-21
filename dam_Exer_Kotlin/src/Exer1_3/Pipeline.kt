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

    /** Função que corre o input passado pelo parametro por cada stage e devolve o resultado final
     * Parametro: input - É passado como parametro uma lista de String
     * Return: List<String> - Retorna uma lista de string (resultado final)
     */
    fun execute(input: List<String>): List<String> {
        var result = input
        for (stage in stages){
            result = stage.second(result)
        }
        return result
    }

    /**
     * Função que printa o nome de cada stage
     */
    fun describe(){
        stages.forEachIndexed { index, stage ->
            println("${index+1} ${stage.first}")}
    }
}