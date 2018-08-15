package com.nk.domain


class CoffeeMachine {

    var price = DEFAULT_COFFEE_PRICE
    var on: Boolean = false
    var coffeCount: Int = 0
    var dollars: Int = 0
    var coffees: Int = 0
    var message: String? = null

    fun pressButton(): Boolean {
        if (!on) {
            return false
        }

        if (coffees == 0) {
            message = "Error: No coffees left"
            return false
        }

        if (dollars < price) {
            message = "Error: Insufficient money"
            return false
        }

        coffees--
        dollars = 0
        message = "Enjoy your coffee!"
        return true
    }

    fun insertOneEuroCoin(dollars: Int) {
        this.dollars += dollars
    }

    companion object {

        internal val DEFAULT_COFFEE_PRICE = 2
    }

}