package Exer1_1

import Exer1_1.Event.Login
import Exer1_1.Event.Logout
import Exer1_1.Event.Purchase

sealed class Event {
    data class Login(val username: String, val timestamp: Long) : Event()
    data class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event()
    data class Logout(val username: String, val timestamp: Long) : Event()
}
    fun List<Event>.filterByUser(username: String): List<Event> {
        val eventsByUser = filter { it ->
            when (it) {
                is Login    -> it.username == username
                is Purchase -> it.username == username
                is Logout   -> it.username == username
            }
        }
        return eventsByUser
    }

    fun List<Event>.totalSpent(username: String): Double {
        val total = filterIsInstance<Purchase>()
                    .filter {it.username == username}
                    .sumOf { it.amount }

        return total
    }

    fun processEvents(events: List<Event>, handler: (Event) -> Unit){
        events.forEach { handler(it) }
    }

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
            is Login -> println("[LOGIN]  ${it.username} logged in at t=${it.timestamp}")
            is Purchase -> println("[PURCHASE]  ${it.username} spent $${it.amount} at t=${it.timestamp}")
            is Logout -> println("[LOGOUT]  ${it.username} logged out at t=${it.timestamp}")
        }
    }

    println("Total spent by alice: $${"%.2f".format(events.totalSpent("alice"))}")

    println("Total spent by bob: $${"%.2f".format(events.totalSpent("bob"))}")

    println("Events for alice:")
    for (event in events.filterByUser("alice")) {
        println(event)
    }
}
