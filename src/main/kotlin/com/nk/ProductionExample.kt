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
    test3()
    /*
    val allDeals = findAllDeals()
            .observeOn(Schedulers.io())
            .subscribe {
                println(" OUT ===>  $it")
            }
            */
    Thread.sleep(5000)
}

fun test(firstFunction: (MyData) -> Int,
         secondFunction: (MyData) -> Int,
         myData: MyData) {

    firstFunction(myData)
    secondFunction(myData)

}

fun test2(firstFunction: (MyData) -> Int,
          secondFunction: (MyData) -> Int): (MyData, MyData) -> Unit =
        { t1, t2 ->
                println(firstFunction(t1))
                println(secondFunction(t2))

        }


fun test3() {
    val firstFunction: (MyData) -> Int = { it.number }
    val secondFunction: (MyData) -> Int = { ++it.number }

    test2(firstFunction, secondFunction)(MyData(5), MyData(9))
}