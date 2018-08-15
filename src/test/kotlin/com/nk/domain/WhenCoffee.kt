package com.nk.domain

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import com.tngtech.jgiven.annotation.ExpectedScenarioState



open class WhenCoffee : Stage<WhenCoffee> (){

    @ExpectedScenarioState
    open lateinit var coffeeMachine: CoffeeMachine

    @ProvidedScenarioState
    open var  coffeeServed: Boolean = false

    open fun `I_insert_$_one_euro_coins`(euros: Int): WhenCoffee {
        coffeeMachine!!.insertOneEuroCoin(euros)
        return self()
    }

    open fun I_press_the_coffee_button(): WhenCoffee {
        coffeeServed = coffeeMachine!!.pressButton()
        return self()
    }

    open fun `I_make_coffee_for_the_$_time`(nr: Int): WhenCoffee {
        coffeeMachine!!.coffeCount = nr
        return self()
    }

}