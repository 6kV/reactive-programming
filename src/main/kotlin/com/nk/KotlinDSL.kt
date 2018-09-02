package com.nk

data class Person(var name: String? = null,
                  var age: Int?= null,
                  var address: Address?= null)


data class Address(var street: String? = null)

fun person(block: (Person) -> Unit): Person {
    val person = Person();
    block(person)
    return person
}


val nizar = person {
    it.name = "nizar"
    it.age = 29
}

fun person2(block: Person.()-> Unit): Person {
    val person = Person()
    person.block()
    return person
}

val messi = person2 {
    name = "Messi"
    age = 29
}

fun person3(block: Person.()-> Unit) = Person().apply { block }

fun Person.address(block: Address.() -> Unit) {
    address = Address().apply { block }
}


val ronaldo = person3 {
    name = "ronaldo"
    address {
        street = "street"
    }
}

// Function type is just a syntactic sugar for an interface
class MyFunction: ()->Unit {

    override fun invoke() {
        println("I am called")
    }
}

fun main(args: Array<String>) {
    val myFunction = MyFunction()
    myFunction()

    val greet: ()-> Unit = { println("Hello") }

    greet()

    val length : Person.() -> Int = { age!! +1  }

    val person = Person(
            age = 3
    )

    println("length(person) = ${length(person)}")

    val squareTest : Int.() -> Int = {this*this}

    squareTest(33)

}