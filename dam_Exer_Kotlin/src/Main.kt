import Event.Login
import Event.Logout
import Event.Purchase

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

    processEvents(events){
        when(it) {
            is Login -> println("[LOGIN]  ${it.username} logged in at t=${it.timestamp}")
            is Purchase -> ("[PURCHASE]  ${it.username} spent $${it.amount} at t=${it.timestamp}")
            is Logout -> ("[LOGOUT]  ${it.username} logged out at t=${it.timestamp}")
        }
    }
}