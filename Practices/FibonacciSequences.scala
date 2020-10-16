// ALGORITHM 3: ITERATIVE VERSION

def fibonacci(num:Int):Int={
    var a = 0
    var b = 1
    var c = 0
    for(k <- Range(0,num)){
        c = b + a 
        a = b 
        b = c 
    }
    return a
}

fibonacci(5)
fibonacci(10)
fibonacci(15)

// CASTILLO SOLIS FABIAN EDUARDO
// In the first line of algorithm 3, we create a function that will receive and throw an Integer
// as a result, then we define 3 variables a = 0, b = 1 and c = 0. Based on the algorithm structure provided
// by the teacher we create a for cycle which will iterate from 0 to the number that we give to the algorithm.
// This for cycle will do 3 things: first will add b + a to the var c, then will give the value on b to the var a
// and finally will assign the value from c to the var b. It will return the result.

// ALGORITHM 4: ITERATIVE VERSION OF TWO VARIABLES

def fibonacci(num:Int):Int={
    var a = 0
    var b = 1
    for(k <- Range(0,num)){
        b = b + a
        a = b - a
    }
    return a
}

fibonacci(5)
fibonacci(10)
fibonacci(15)

// CASTILLO SOLIS FABIAN EDUARDO
// In the first line of algorithm 4, we create a function that will receive and throw an Integer
// as a result, then we define 2 variables a = 0 and b = 1. Based on the algorithm structure provided
// by the teacher we create a for cycle which will iterate from 0 to the number that we give to the algorithm.
// This for cycle will do 2 things: first will add b + a to the var b and then will substract a from b and that
// will be assigned to the var a. Finally it will return the result.