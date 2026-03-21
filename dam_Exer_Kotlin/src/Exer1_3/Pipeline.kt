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
            println("${index+1}. ${stage.first}")}
    }

    /**
     * Função que dados dois nomes de etapas já existentes no pipeline,
     * é criado uma etapa que aplica as duas em sequência.
     * Parametro: firstName, secondName, newName - String
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
     * Função que dados dois pipelines, corre-se o mesmo input em ambos e é devolvido os dois resultados num Pair.
     * Parametro: input - List<String>
     * Paramtro: Other - Pipeline
     * Return: Pair<List<String>, List<String>>
     */
    fun fork(input: List<String>, other: Pipeline): Pair<List<String>, List<String>>{
        val resultado1 = execute(input)
        val resultado2 = other.execute(input)

        return Pair(resultado1, resultado2)
    }
}

/**
 * Função que cria um pipeline e aplica um block
 * Parametro: Block - uma lambda com receiver
 * Return: Pipeline - retorna um pipeline com o block aplicado
 */
fun buildPipeline(block :Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.block()
    return pipeline
}