package Exer1_1

import Exer1_1.Event.Login
import Exer1_1.Event.Logout
import Exer1_1.Event.Purchase

/**
 * Sealed class que representa os diferentes tipos de eventos do sistema.
 * Garante que apenas os tipos definidos podem existir,
 * permitindo um bloco when exaustivo sem necessidade de "else".
 */
sealed class Event {

    /**
     * Evento de autenticação de um utilizador.
     * @param username nome do utilizador
     * @param timestamp momento em que o login ocorreu
     */
    data class Login(val username: String, val timestamp: Long) : Event()

    /**
     * Evento de compra realizada por um utilizador.
     * @param username nome do utilizador
     * @param amount valor gasto na compra
     * @param timestamp momento em que a compra ocorreu
     */
    data class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event()

    /**
     * Evento de saída de um utilizador.
     * @param username nome do utilizador
     * @param timestamp momento em que o logout ocorreu
     */
    data class Logout(val username: String, val timestamp: Long) : Event()
}

/**
 * Filtra a lista de eventos pelo nome do utilizador.
 * @param username nome do utilizador a filtrar
 * @return lista de eventos associados ao utilizador
 */
fun List<Event>.filterByUser(username: String): List<Event> {
    val eventsByUser = filter {
        when (it) {
            is Login    -> it.username == username
            is Purchase -> it.username == username
            is Logout   -> it.username == username
        }
    }
    return eventsByUser
}

/**
 * Calcula o total gasto por um utilizador em todos os eventos de compra.
 * @param username nome do utilizador
 * @return total gasto em Double, formatado com 2 casas decimais
 */
fun List<Event>.totalSpent(username: String): Double {
    return filterIsInstance<Purchase>()
        .filter { it.username == username }
        .sumOf { it.amount }
}

/**
 * Função de ordem superior que aplica um handler a cada evento da lista.
 * @param events lista de eventos a processar
 * @param handler lambda aplicada a cada evento
 */
fun processEvents(events: List<Event>, handler: (Event) -> Unit) {
    events.forEach { handler(it) }
}

/**
 * Função principal que demonstra o uso do sistema de eventos.
 * Processa uma lista de eventos, calcula totais gastos e filtra por utilizador.
 */
fun main() {
    val events = listOf(
        Login("alice", 1_000),
        Purchase("alice", 49.99, 1_100),
        Purchase("bob", 19.99, 1_200),
        Login("bob", 1_050),
        Purchase("alice", 15.00, 1_300),
        Logout("alice", 1_400),
        Logout("bob", 1_500)
    )

    processEvents(events) {
        when (it) {
            is Login    -> println("[LOGIN]  ${it.username} logged in at t=${it.timestamp}")
            is Purchase -> println("[PURCHASE]  ${it.username} spent $${it.amount} at t=${it.timestamp}")
            is Logout   -> println("[LOGOUT]  ${it.username} logged out at t=${it.timestamp}")
        }
    }

    println("Total spent by alice: $${"%.2f".format(events.totalSpent("alice"))}")
    println("Total spent by bob: $${"%.2f".format(events.totalSpent("bob"))}")

    println("Events for alice:")
    for (event in events.filterByUser("alice")) {
        println(event)
    }
}