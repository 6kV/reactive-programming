package com.nk

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


fun main(args: Array<String>) {
    // First Exemple observable
    val observable = listOf("1", "2", "3").toObservable()

    observable.subscribeBy (
        onNext = ::println,
        onError = { it.printStackTrace() },
        onComplete = { println("Done!") }
    )

    // Second Exemple
    val subject:Subject<Int> = PublishSubject.create()

    subject.map(::isEven).subscribe({
        println("The number is ${(if (it) "Even" else "Odd")}" )})

    subject.onNext(4)
    subject.onNext(9)
}

fun isEven(number: Int) = number % 2 == 0