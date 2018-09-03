package com.nk

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    Observable.range(0,1000)
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(Schedulers.computation())
            .subscribeBy {
                Thread.sleep(100)
                println("it = ${it}")
            }

    Thread.sleep(10000)
}
