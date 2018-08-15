package com.nk.domain

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ExtendedDescription
import com.tngtech.jgiven.format.BooleanFormatter
import com.tngtech.jgiven.annotation.ExpectedScenarioState
import com.tngtech.jgiven.annotation.Format
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat


open class ThenCoffee : Stage<ThenCoffee> (){

    @ExpectedScenarioState
    private val coffeeServed: Boolean = false

    @ExpectedScenarioState
    private val coffeeMachine: CoffeeMachine? = null

    open fun `I_$shouldOrShouldNot_be_served_a_coffee`(
            @Format(value = BooleanFormatter::class, args = arrayOf("should", "should not" )) shouldOrShouldNot: Boolean) {
        I_should_be_served_a_coffee(shouldOrShouldNot)
    }

    open fun I_should_not_be_served_a_coffee(): ThenCoffee {
        return I_should_be_served_a_coffee(false)
    }

    open fun I_should_be_served_a_coffee(b: Boolean): ThenCoffee {
        Assertions.assertThat(coffeeServed).isEqualTo(b)
        return self()
    }

    open fun a_coffee_should_be_served(): ThenCoffee {
        return I_should_be_served_a_coffee(true)
    }

    open fun no_coffee_should_be_served(): ThenCoffee {
        return self()
    }

    open fun an_error_should_be_shown(): ThenCoffee {
        assertThat(coffeeMachine!!.message).startsWith("Error")
        return self()
    }

    open fun `the_message_$_is_shown`(message: String): ThenCoffee {
        assertThat(coffeeMachine!!.message).isEqualTo(message)
        return self()
    }

    open fun `there_are_$_coffees_left_in_the_machine`(coffees_left: Int): ThenCoffee {
        assertThat(coffeeMachine!!.coffees).isEqualTo(coffees_left)
        return self()
    }

    open fun I_should_be_served_a_coffee(): ThenCoffee {
        return I_should_be_served_a_coffee(true)
    }

    open fun no_error_is_shown(): ThenCoffee {
        assertThat(coffeeMachine!!.message).isNull()
        return self()
    }

    open fun the_result_is(result: String): ThenCoffee {
        if (coffeeMachine!!.coffeCount == 1) {
            assertThat(result).isEqualTo("quite ok")
        } else {
            assertThat(result).isEqualTo("well-done")
        }
        return self()
    }

    @ExtendedDescription("This step is still visible in the report, but was actually not executed. It is marked as skipped in the report.")
    open fun steps_following_a_failed_step_should_be_skipped() {
        // just here for the report
    }

}