// def isEven(num:Int): Boolean = {
//     return num%2 == 0
// }
// def isEven(num:Int): num%2 == 0
// println(isEven(6))
// println(isEven(3))
// Practice 3, analyse the following code with your own words

/*
Angeles Valadez Jonathan
The function listEvens was declared so that a new list would be created from an existing list of numbers
and it would return a string value, it starts with a for going through the list element by element
calculating the module of a number which is 2, and if the residual comes back as 0 then it is even,
if it comes back different then it means it is an odd number, and once it's done with all the elements,
it returns the string "Done".
*/

def listEvens(list:List[Int]): String ={
    for(n <- list){
        if(n%2==0){
            println(s"$n is even")
        }else{
            println(s"$n is odd")
        }
    }
    return "Done"
}

/*
Angeles Valadez Jonathan
Creation of 2 lists and using the function listEvens to determine which nmumbers are odd and which are even
*/
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)

// CASTILLO SOLIS FABIAN EDUARDO:
// The first line creates a new function called "listEvens" which will take a list of integers and return a string
// Then the for cycle evaluates the integers on the list and if the module for the number on the list equals to zero
// The function will print that the number readed is even. If the module is not zero then the function will print that
// the number readed is odd. At the end the function will return a "Done" just to let the user know that the job is done.
// Finally we create the lists that will be evaluated by the function and test the function.


//3 7 afortunado
/*
Angeles Valadez Jonathan
The function afortunado utilizes a list so that it can make a sum with a condition,
if the element in the current index is different than 7, it will add the number to 
the variable 'res', but it is equal to 7, it will sum 14 to the variable 'res', at the end of the cycle,
it will print the final result of 'res'.
*/
def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}

/*
Angeles Valadez Jonathan
The creation of the list 'af' and the usage of the function
afortunado.
*/
val af= List(1,7,7)
println(afortunado(af))

// CASTILLO SOLIS FABIAN EDUARDO:
// This line creates a new function called "afortunado" which will take a list of integers
// This line create and initialized a variable called "res" for result
// Here we can see a cycle for which reads a the list of integers that we talked about above
// This is an If statement where if the number on the list it's the number 7 will add the result
// plus 14
// In case the number on the list it's not 7, it will add the result plus the number readed
// Finally it will throw the number resulting from that cycle
//List with integers
// Calling the function afortunado and giving the list af as a parameter


// Code 3
/*
Angeles Valadez Jonathan
The function balance starts with 2 variables, 'primera' and
'segunda', primera starts at 0 and segunda starts with the sum of the
numbers in the list that you give when you call this function, the function
returns a true if at any moment primera and segunda are the same, the stored function
for checks the list through each index and adds the number in the current index to primera,
while segunda subtracts the number in the index from itself.
*/
def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

/*
Angeles Valadez Jonathan
Creation of the lists to use the function balance and check if it works.
*/

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)

/*
Angeles Valadez Jonathan
The function palindromo, basically checks if a string is a palindrome, which is
a word that stays the same even if it is written backwards.
*/

def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}

/*
Angeles Valadez Jonathan
The creation of the string values so we can check if the function 'palindromo'
is working correctly.
*/

val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))

// CASTILLO SOLIS FABIAN EDUARDO
// This line defines a function called "palindromo" which will take a string and return a true or false
// This evaluates if the string readed equals to the same string but in reverse
// Strings that will be used by the function
// Calling the function with the strings as parameters