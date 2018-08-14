package com.nk

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


// Flowables
fun main(args: Array<String>) {
    Flowable.range(1,10)
            .map(::MyItem)
            .observeOn(Schedulers.io())
            .subscribe(
                    {
                        println("Received $it")
                        runBlocking { delay(50) }//(4)
                    },
                    {it.printStackTrace()})
    runBlocking { delay(600) }


    val source = Observable.range(1, 1000)
    source.toFlowable(BackpressureStrategy.DROP)
            .map(::MyItem)
            .observeOn(Schedulers.io())
            .subscribe(
                    {
                        println("Received $it")
                        runBlocking { delay(50) }//(4)
                    },
                    {it.printStackTrace()})
    runBlocking { delay(60000) }

}

data class MyItem(val id: Int) {
    init {
        println(" itemId = ${id}")
    }
}