package Exer1_1

import Exer1_1.Event.Login
import Exer1_1.Event.Logout
import Exer1_1.Event.Purchase

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
            is Purchase -> ("[PURCHASE]  ${it.username} spent $${it.amount} at t=${it.timestamp}")
            is Logout -> ("[LOGOUT]  ${it.username} logged out at t=${it.timestamp}")
        }
    }
}