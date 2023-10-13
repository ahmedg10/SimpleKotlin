package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(word: Any): String {
    when(word) {
        "Hello" -> return("world")
        is String -> return("Say what?")
        0 -> return("zero")
        1 -> return ("one")
        in 2..10 -> return("low number")
        is Int -> return("a number")
        else -> return("I don't understand")
    }
}
// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(one: Int, two: Int): Int {
    return one + two
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(one:Int, two: Int): Int {
    return one - two
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(val1: Int, val2: Int, operation: (Int, Int) -> Int): Int {
    return operation(val1, val2)
}
// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int){
    val debugString: String
        get() =  "[Person firstName:$firstName lastName:$lastName age:$age]"

}
// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(val amount: Int, val currency: String) {
    //we can use it set specific requirements for the class 
    init{
        require(amount >= 0){"Amount can't be less than zero"}
        require(currency in setOf("USD", "EUR", "CAN", "GBP")) { "Invalid currency" }
    }

    fun convert(newCurrency: String): Money {
        val newAmount = when (this.currency) {
            "USD" -> when (newCurrency) {
                "GBP" -> 5
                "EUR" -> 15
                "CAN" -> 15
                else -> 10
            }
            "GBP" -> when (newCurrency) {
                "USD" -> 10
                "EUR" -> 15
                else -> 5
            }
            "EUR" -> when (newCurrency) {
                "GBP" -> 5
                "USD" -> 10
                else -> 15
            }
            "CAN" -> when (newCurrency) {
                "USD" -> 12
                else -> 10 // Default value if "CAN" is converted to an unknown currency
            }
            else -> 10 // Default value if the source currency is unknown
        }
        return Money(newAmount, newCurrency)
    }
    

    operator fun plus(other: Money): Money {
        var convertSecondMoney = other.convert(this.currency)

        return(Money(convertSecondMoney.amount + this.amount, this.currency))
    }
}
