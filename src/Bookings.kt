import java.math.BigDecimal

interface BookingManager {

    val version : String

    fun isSeatFree(seat: Seat) : Boolean
    fun reserveSeat(seat: Seat, customerID: Long) : Boolean
    fun systemStatus() = "Al operations are functional"
}

class UnauthorizedUserException : Throwable()

open class BasicBookingManager(authorizationKey: String) : BookingManager {
    override val version = "1.0"

    override fun isSeatFree(seat: Seat) = true
    override fun reserveSeat(seat: Seat, customerID: Long) = false

    init {
        if (authorizationKey != "1234") throw UnauthorizedUserException()
    }
}

class AdvancedBookingManager : BasicBookingManager("1234"), java.io.Closeable {

    override val version = "2.0"

    fun howManyBookings() = 10

    override fun close() {}
}

fun String.toSentenceCast() : String {
    return this[0].toUpperCase() + this.substring(1)
}

fun main(args: Array<String>) {
    println(AdvancedBookingManager().isSeatFree(Seat(1, 1, BigDecimal.ZERO, "")))

    val myList = mutableListOf<Int>()

    val greeting = "welcome to the system"

    println(greeting.toSentenceCast())

}