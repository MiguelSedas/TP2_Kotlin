import Event.Login
import Event.Logout
import Event.Purchase

sealed class Event {
    data class Login(val username: String, val timestamp: Long) : Event()
    data class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event()
    data class Logout(val username: String, val timestamp: Long) : Event()
}
    fun List<Event>.filterByUser(username: String): List<Event> {
        val eventsByUser = filter { it ->
            when (it) {
                is Event.Login    -> it.username == username
                is Event.Purchase -> it.username == username
                is Event.Logout   -> it.username == username
            }
        }
        return eventsByUser
    }

    fun List<Event>.totalSpent(username: String): Double {
        val total = filterIsInstance<Event.Purchase>()
                    .filter {it.username == username}
                    .sumOf { it.amount }

        return total
    }

    fun processEvents(events: List<Event>, handler: (Event) -> Unit){
        events.forEach { handler(it) }
    }

fun main() {
    val events = listOf(
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500)
    )

    processEvents(events) {
        when (it) {
            is Login -> println("[LOGIN]  ${it.username} logged in at t=${it.timestamp}")
            is Purchase -> println("[PURCHASE]  ${it.username} spent $${it.amount} at t=${it.timestamp}")
            is Logout -> println("[LOGOUT]  ${it.username} logged out at t=${it.timestamp}")
        }
    }
}
