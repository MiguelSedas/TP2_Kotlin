sealed class Event {
    data class Login(val username: String, val timestamp: Long) : Event()
    data class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event()
    data class Logout(val username: String, val timestamp: Long) : Event()

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
}