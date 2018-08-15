package com.nk.domain

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.format.BooleanFormatter
import com.tngtech.jgiven.annotation.ExtendedDescription
import com.tngtech.jgiven.annotation.Format
import com.tngtech.jgiven.annotation.ProvidedScenarioState



open class GivenCoffee : Stage<GivenCoffee>(){

    @ProvidedScenarioState
    open lateinit var coffeeMachine: CoffeeMachine

    @ProvidedScenarioState
    private val euros: Int = 0

    @ExtendedDescription("An empty coffee machine that is already turned on.<br>" + "The coffee price is set to 2 EUR.")
    open fun a_coffee_machine(): GivenCoffee {
        coffeeMachine = CoffeeMachine()
        coffeeMachine!!.on = true
        return this
    }

    @ExtendedDescription("The number of coffees in the machine is set to the given value.")
    open fun `there_are_$_coffees_left_in_the_machine`(coffees: Int): GivenCoffee {
        coffeeMachine!!.coffees = coffees
        return this
    }

    open fun `the_coffee_costs_$_euros`(price: Int): GivenCoffee {
        coffeeMachine!!.price = price
        return this
    }

    open fun `the_machine_is_$onOrOff`(@Format(value = BooleanFormatter::class, args =  arrayOf("on", "off")) onOrOff: Boolean): GivenCoffee {
        coffeeMachine!!.on = onOrOff
        return this
    }

    open fun there_are_no_more_coffees_left(): GivenCoffee {
        return `there_are_$_coffees_left_in_the_machine`(0)
    }

    open fun the_machine_is_turned_off(): GivenCoffee {
        coffeeMachine!!.on = false
        return this
    }

    open fun an_empty_coffee_machine(): GivenCoffee {
        return a_coffee_machine()
                .and().there_are_no_more_coffees_left()
    }

    open fun an_exception_with_a_very_long_message(): GivenCoffee {
        throw RuntimeException("This is a very long exception message that should be wrapped at some point " + "in the report and it is even longer than that")
    }
}
