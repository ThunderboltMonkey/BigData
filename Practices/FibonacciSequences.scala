//ALGORITHM 1: DESCENDING TAIL RECURSION VERSION
/*
ANGELES VALADEZ JONATHAN
This algorithm is a tail based algorithm and it is built for the evasion of the
stack overflow error, using 3 variables, n which is the number you wish to know
the fibonacci value for, and, 'a' and 'b', which are values that store intermediate
values, basically they are accumulators, where 'a' starts with 0 and 'b' starts with 1.
When n = 0, b will be printed with the fibonacci value.
*/
def fibonacciR(n:Int, a:Int, b:Int) : Int = {
 if ( n == 0 )
  b
 else
  fibonacciR( n - 1, b, a + b )
}

fibonacciR(5,0,1)
fibonacciR(10,0,1)
fibonacciR(20,0,1)

//ALGORITHM 2: EXPLICIT FORMULA VERSION
/*
ANGELES VALADEZ JONATHAN
This algorithm uses the explicit formula for the fibonacci series, this formula isn't as
precise as when you use the conventional methods like cycles or recursion, at least that's
the conclusion that I got to, given that since it uses a lot of decimals, it get go way off
the mark or even closer to it, I tried using the formula in various ways(as you can see with the
commented values), but the result isn't very precise.
*/
def fibonacciFormula(n:Double) : Double = {
    var a: Double = 0
    var b: Double = 0
    if ( n < 2 )
        b
    else
        //a = ((1 + (Math.sqrt(5)) ) /2)
        a = 1.61
        b = (( ( (Math.pow(a,n)) - (Math.pow((1-a),n)) ) / (Math.sqrt(5)) ))
        //b = (( ( (Math.pow(1.61,n)) - (Math.pow(-0.61,n) )) / (1.61) ))
        b
}
fibonacciFormula(5)
fibonacciFormula(8)
fibonacciFormula(10)


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

// ALGORITHM 5: ITERATIVE VERSION WITH VECTORS

def fibonacci(num:Int):Int={
    if(num<2){
        return num
    }else{
        var vector = new Array[Int](num+1)
        vector(0) = 0
        vector(1) = 1
        for (k <- Range(2,num+1)){
            vector(k)=vector(k-1)+vector(k-2)
        }
        return vector(num)
    }
}

fibonacci(5)
fibonacci(10)
fibonacci(15)

// CASTILLO SOLIS FABIAN EDUARDO
// In the first line of algorithm 5, we create a function that will receive and throw an Integer
// as a result, then create an if statement which if the number is less than 2 it will return the same exact number.
// If not then, we declared a vector which will take integers, in the position 0 we assign a zero, and in the position
// 1 we will assign the number one, then we create a for cycle which will iterate from 0 to the number that we give to the algorithm.
// This for cycle will assign to the vector in the current position (k) is going to assign to the vector in position k, the sum resulting 
// from the last and penultimate position, finally it will return the calculated result.