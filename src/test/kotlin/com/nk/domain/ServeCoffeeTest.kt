package com.nk.domain

import com.tngtech.jgiven.annotation.CaseAs;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.jgiven.StepFunction;
import com.tngtech.jgiven.annotation.Description;
import com.tngtech.jgiven.junit.ScenarioTest;
import com.tngtech.jgiven.tags.FeatureCaseDiffs;
import com.tngtech.jgiven.tags.FeatureDataTables;
import com.tngtech.jgiven.tags.FeatureTagsWithCustomStyle
import com.tngtech.jgiven.tags.Issue;


@RunWith(DataProviderRunner::class)
@Description("In order to refresh myself</br>" +
        "as a customer</br>" +
        "I want coffee to be served")
open class ServeCoffeeTest : ScenarioTest<GivenCoffee, WhenCoffee, ThenCoffee>() {

    @Test
    @Throws(Exception::class)
    @ExampleCategory
    fun an_empty_coffee_machine_cannot_serve_any_coffee() {

        given().an_empty_coffee_machine()

        `when`().`I_insert_$_one_euro_coins`(5)
                .and().I_press_the_coffee_button()

        then().an_error_should_be_shown()
                .and().no_coffee_should_be_served()
    }

    @Test
    fun no_coffee_left_error_is_shown_when_there_is_no_coffee_left() {
        given().an_empty_coffee_machine()
        `when`().`I_insert_$_one_euro_coins`(5)
                .and().I_press_the_coffee_button()
        then().`the_message_$_is_shown`("Error: No coffees left")
    }

    @Test
    @Throws(Exception::class)
    fun not_enough_money_message_is_shown_when_insufficient_money_was_given() {

        given().a_coffee_machine()
                .and().`there_are_$_coffees_left_in_the_machine`(2)
        `when`().`I_insert_$_one_euro_coins`(1)
                .and().I_press_the_coffee_button()
        then().`the_message_$_is_shown`("Error: Insufficient money")
    }

    @Test
    @FeatureDataTables
    @DataProvider("0, 0, Error: No coffees left", "0, 1, Error: No coffees left", "1, 0, Error: Insufficient money", "0, 5, Error: No coffees left", "1, 5, Enjoy your coffee!")
    @Throws(Exception::class)
    fun correct_messages_are_shown(coffees_left: Int, number_of_coins: Int, message: String) {
        given().a_coffee_machine()
                .and().`there_are_$_coffees_left_in_the_machine`(coffees_left)
        `when`().`I_insert_$_one_euro_coins`(number_of_coins)
                .and().I_press_the_coffee_button()
        then().`the_message_$_is_shown`(message)
    }

    @Test
    @FeatureDataTables
    @Issue("#15")
    @DataProvider("1", "3", "10")
    fun serving_a_coffee_reduces_the_number_of_available_coffees_by_one(initial_coffees: Int) {
        given().a_coffee_machine()
                .and().`there_are_$_coffees_left_in_the_machine`(initial_coffees)
        `when`().`I_insert_$_one_euro_coins`(2)
                .and().I_press_the_coffee_button()
        then().a_coffee_should_be_served()
                .and().`there_are_$_coffees_left_in_the_machine`(initial_coffees - 1)
    }

    @Test
    @Throws(Exception::class)
    fun a_turned_off_coffee_machine_cannot_serve_coffee() {

        given().a_coffee_machine()
                .and().the_machine_is_turned_off()

        `when`().I_press_the_coffee_button()

        then().no_coffee_should_be_served()

    }

    @FeatureTagsWithCustomStyle
    @Test
    @DataProvider("true, 1, 1, false", "true, 1, 2, true", "true, 0, 2, false", "false, 1, 2, false")
    fun buy_a_coffee(onOrOff: Boolean, coffees: Int, dollars: Int, shouldOrShouldNot: Boolean) {

        given().a_coffee_machine().and().`there_are_$_coffees_left_in_the_machine`(coffees).and().`the_machine_is_$onOrOff`(onOrOff).and()
                .`the_coffee_costs_$_euros`(2)

        `when`().`I_insert_$_one_euro_coins`(dollars).and().I_press_the_coffee_button()

        then().`I_$shouldOrShouldNot_be_served_a_coffee`(shouldOrShouldNot)
    }

    @Test
    @FeatureCaseDiffs
    @DataProvider("true", "false")
    fun turned_off_machines_should_not_serve_coffee(onOrOff: Boolean) {
        given().a_coffee_machine()
                .and().`there_are_$_coffees_left_in_the_machine`(2)
                .and().`the_machine_is_$onOrOff`(onOrOff)

        `when`().`I_insert_$_one_euro_coins`(2).and().I_press_the_coffee_button()

        if (onOrOff) {
            then().I_should_be_served_a_coffee()
        } else {
            then().I_should_not_be_served_a_coffee().and().no_error_is_shown()
        }

    }

    @Test
    fun intro_words_are_not_required() {
        given().a_coffee_machine()
                .`the_coffee_costs_$_euros`(5)
                .`there_are_$_coffees_left_in_the_machine`(3)

        `when`().I_press_the_coffee_button()

        then().an_error_should_be_shown()
                .no_coffee_should_be_served()
    }

    // tag::dataprovider[]
    @Test
    @DataProvider("1, 1", "0, 2", "1, 0")
    fun coffee_is_not_served(coffees: Int, euros: Int) {
        given().a_coffee_machine()
                .and().`the_coffee_costs_$_euros`(2)
                .and().`there_are_$_coffees_left_in_the_machine`(coffees)

        `when`().`I_insert_$_one_euro_coins`(euros)
                .and().I_press_the_coffee_button()

        then().I_should_not_be_served_a_coffee()
    }
    // end::dataprovider[]

}
