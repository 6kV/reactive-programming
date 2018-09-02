package com.nk

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

fun findAllDeals() = Flowable.fromIterable<MyData>(MyIterable())
        // Buffer du nombre delements
        .buffer(100)
                                                                                                                                                                                            .flatMap {
            Flowable.just(it)
                    .subscribeOn(Schedulers.computation())
                    .doOnNext {
                        println(" PROCESS = $it")
                        Thread.sleep(100)
                    }
        }!!

data class MyData(var number: Int)


class MyIterable : Iterable<MyData> {

    override fun iterator(): Iterator<MyData> {
        return MyIterator()
    }

}


class MyIterator : Iterator<MyData> {

    var index = 0
    var max = 10000

    override fun hasNext(): Boolean = index < max

    override fun next(): MyData {
        println("IN  ===>  ${index}")
        return MyData(++index)
    }
}

fun main(args: Array<String>) {

    val allDeals = findAllDeals()
            .observeOn(Schedulers.io())
            .subscribe {
                println(" OUT ===>  $it")
            }


    Thread.sleep(5000)


}