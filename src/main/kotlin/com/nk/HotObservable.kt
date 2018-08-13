package com.nk

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val connectableObservable = listOf("String 1","String 2","String 3","String 4","String5").toObservable().publish()

    connectableObservable.subscribe({ println("Subscription 1: $it") })

    connectableObservable.map(String::reversed)
            .subscribe({ println("Subscription 2 $it")})

    connectableObservable.connect()

    connectableObservable.subscribe({ println("Subscription 3: $it") })//Will not receive emissions
}