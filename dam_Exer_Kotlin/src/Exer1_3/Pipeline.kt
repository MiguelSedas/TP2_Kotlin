package Exer1_3

class Pipeline{

    /**
     * Variavel: stages - guarda uma lista de transformações
     */
    private val stages = mutableListOf< Pair<String, (List<String>) -> List<String>>>()

    /**
     * Adiciona um nome a um pipeline
     * @param name String para descrever o pipeline
     * @param transform função lambda que recebe uma list e retorna o mesmo tipo
     */
    fun addStage(name : String, transform: (List<String>) -> List<String>) {
        stages.add(name to transform)
    }

    /**
     * Corre o input passado pelo parametro por cada stage e devolve o resultado final
     * @param input lista de String
     * @return lista de string (resultado final)
     */
    fun execute(input: List<String>): List<String> {
        var result = input
        for (stage in stages){
            result = stage.second(result)
        }
        return result
    }

    /**
     * Printa o nome de cada stage
     */
    fun describe(){
        stages.forEachIndexed { index, stage ->
            println("${index+1}. ${stage.first}")}
    }

    /**
     * Dados dois nomes de etapas já existentes no pipeline cria uma etapa que aplica as duas em sequência.
     * @param firstName Nome de um Pipeline existente
     * @param secondName Nome de um Pipeline existente
     * @param newName Nome do novo Pipeline
     */
    fun compose(firstName: String, secondName: String, newName: String){
        val f = stages.find { it.first == firstName }?.second
        val g = stages.find { it.first == secondName }?.second

        if (f != null && g != null){
            val h: (List<String>) -> List<String> = { input -> g(f(input)) }
            stages.add(newName to h)
        }
    }

    /**
     * Dados dois pipelines, corre o mesmo input em ambos e é devolvido os dois resultados num Pair.
     * @param input List<String>
     * @param other Pipeline
     * @return Pair<List<String>, List<String>>
     */
    fun fork(input: List<String>, other: Pipeline): Pair<List<String>, List<String>>{
        val resultado1 = execute(input)
        val resultado2 = other.execute(input)

        return Pair(resultado1, resultado2)
    }
}

/**
 * Função que cria um pipeline e aplica um block
 * @param block função lambda com receiver
 * @return Pipeline - retorna um pipeline com o block aplicado
 */
fun buildPipeline(block :Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.block()
    return pipeline
}