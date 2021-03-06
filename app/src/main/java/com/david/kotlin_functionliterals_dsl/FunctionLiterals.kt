package com.david.kotlin_functionliterals_dsl

/*
  * Implementation of lambdas as high order function, function extensions and function literals to construct DSL functions
*/


//1. classic function declaration
fun appendTagChars(word: String): String {
    return StringBuilder().append("<").append(word).append(">").toString()
}

//2. Extension function
fun String.appendTag():String {
   return StringBuilder().append("<").append(this).append(">").toString()
}

/*
* 3. Lambdas are anonymous functions that can be treated as object been passed as function parameter, be returned by functions, value of variable, etc
* */

val square: (Int) -> Int = { value -> value * value }

/*
* 4. High order functions are functions that receives functions as parameters or returns them
* */

fun passMeAFunction(function: () -> Unit) {
    //do something
    println("I called passMeAFunction")
    //call function whe it is needed
    function()
}


//4.1 return function from another function
fun add(a: Int, b: Int): Int = a + b

fun returnMeAddFunction(): ((Int, Int) -> Int) {
    return ::add
}

//4.2 high order function with parameters
fun passArithmeticOperator(a: Int, b: Int, operator: ( Int, Int) -> Int): Int = operator(a, b)


/*5
* Extension functions applied to functions
  Allows to use the properties of the receiver object using 'this' which is convenient when requiring lambdas as parameter
 */

 fun buildStringExtended(function: (StringBuilder).() -> Unit):String{
     val builder = StringBuilder()
     function(builder)
     return builder.toString()
 }



fun main() {



    //1. println(appendTagChars("David"))

    //2.
    val name = "David"
    println("name has append tag of : ${name.appendTag()}")

    //3.
    println("square of 3 is ${square(3)}")

    /* val sumOf: (Int, Int) -> Int = { a, b -> a + b }
    val result = sumOf(3, 7)
    println(result)*/

    //4.
    val innerFunction: () -> Unit = {
        println("I'm inside the function parameter")
        println("I can calculate inner operation like square of 4 is ${square(4)}")
    }

    passMeAFunction(innerFunction)

    //4.1
    val add = returnMeAddFunction()
    println("sum of 2 and 3 is equal to ${add(2, 3)}")

    //4.2

    val addResult = passArithmeticOperator(7, 7, ::add)
    println("Using add operator with 7 & 7 values return $addResult")

    //5 Constructing lambda with a receiver
    val newString = buildStringExtended {
        append("<")
        append("appending some text from extended function")
        append(">")
    }

    println("Using external function result is $newString")
}


