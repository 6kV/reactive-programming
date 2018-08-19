package com.nk

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) {
    Observable.create<String> {
        it.onNext("hello")
        it.onNext("hello 1")
        it.onNext("hello 2")
        it.onNext("hello 3")
    }

    listOf(1,2,3,4,5,6,7,8)
            .toObservable()
            .subscribe(::println)


    val range = Observable.range(1, 100)
            .subscribeOn(Schedulers.computation())

    range
            .observeOn(Schedulers.computation())
            .subscribe{t ->
                println("${Thread.currentThread()}= $t")
            }

    range.subscribe {t -> println("${Thread.currentThread()}= $t") }

    Thread.sleep(10000L)
}