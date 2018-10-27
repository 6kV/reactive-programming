package com.nk

import java.math.BigDecimal
import java.time.LocalDate

@DslMarker
annotation class SyndicationDsl

@SyndicationDsl
data class House(var name:String = "",
                 var currency: String = "",
                 var rooms: MutableList<Room> = mutableListOf()) {

    operator fun Room.unaryPlus(){
        this@House.rooms.add(this)
    }

    fun havingName(namme: () -> String) {
        this.name = namme()
    }

    fun havingCurrency(namme: () -> String) {
        this.currency = namme()
    }

}

@SyndicationDsl
data class Room(var facilityCode: String = "",
                var amount: BigDecimal = BigDecimal.ZERO)


// infix
// Extension Function
// last Lambda
// lambda with receiver
// operator overloading
// 2 days ago


fun house(block: House.() -> Unit): House {
    val syndication = House()
    block(syndication)
    return syndication
}

fun room(block: Room.() -> Unit) : Room {
    val facility = Room()
    block(facility)
    return facility
}

infix fun Int.days(test: String) : LocalDate {
    if ( test == "ago") return LocalDate.now().minusDays(this.toLong())
    else throw RuntimeException("bad point")
}



fun main(args: Array<String>) {
    // 2 days ago
    val ago = "ago"


    val days = 2 days ago

    println("days = $days")

    val house = house {

        havingName {"the deal name"}
        havingCurrency {  "EUR" }

        +room {
            facilityCode = "F123"
            amount = BigDecimal.ZERO
        }
        +room {
            facilityCode = "F134"
            amount = BigDecimal.TEN
        }

    }

    println("house = $house")
}