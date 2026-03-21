package Exer1_3

fun main(){
    val trim = buildPipeline {
        /**
         * Pipeline para remover espaços antes e no fim da linha
         */
        addStage("Trim"){
            list -> list.map { it.trim() }
        }
        /**
         * Pipeline que fica só com linhas que contêm "ERROR"
         */
        addStage("FilterErrors"){
            list -> list.filter{ it.contains("ERROR") }
        }
        /**
         * Pipeline que converte cada linha para maiúsculas
         */
        addStage("Uppercase"){
            list -> list.map { it.uppercase() }
        }
        /**
         * Pipeline que adiciona o index a cada linha
         */
        addStage("AddIndex"){
            list -> list.mapIndexed { index, line -> "${index + 1}. $line"  }
        }
    }
}