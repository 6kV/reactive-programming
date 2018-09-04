package com.nk

data class Pipeline(var id: String)


fun test(pipeline: Pipeline,
         predicate : (Pipeline) -> Boolean,
         function : (Pipeline) -> Unit) {

    if (predicate(pipeline)) {
        function(pipeline)
    }
}

fun test23(predicate: (Pipeline) -> Boolean,
          function : (Pipeline) -> Unit) : (Pipeline) -> Unit = {
    if ( predicate(it)){
        function(it)
    }
}

fun performOperation(pipeline: Pipeline) :((Pipeline) -> (Boolean)) -> ((Pipeline) -> (Unit)) -> Unit = {
           t1 ->  { t2 -> t2(pipeline) }
}


fun main(args: Array<String>) {
    test(Pipeline("test"),{ it.id == "test"},{ println("$it") })

    val isEqualTest: (Pipeline) -> Boolean = { it.id == "test" }
    val printIt: (Pipeline) -> Unit = { println("$it") }
    val pipeline = Pipeline("test")

    performOperation(pipeline)(isEqualTest)(printIt)
}