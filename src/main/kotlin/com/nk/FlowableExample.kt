package com.nk

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import java.util.*

fun main(args: Array<String>) {

    val myAggregate = MyAggregate()
    println(myAggregate.test())
    myAggregate.value1 = Optional.of(BigDecimal.ONE)

    println(myAggregate.test())

    Observable.fromIterable<MyData>(MyIterable())
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(Schedulers.computation())
            .subscribeBy {
                Thread.sleep(100)
                println("it = ${it}")
            }

    Thread.sleep(10000)
}

class MyAggregate {

    var value1 : Optional<BigDecimal> = Optional.empty()
    var value2 : Optional<BigDecimal> = Optional.empty()


    fun test()= value1.orElseGet { BigDecimal.ZERO }.plus(value2.orElseGet { BigDecimal.ZERO })
}


