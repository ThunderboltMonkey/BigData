# PRACTICE # 1

## 1) Develop an algorithm in Scala that calculates the radius of a circle

	// This function let us print on screen a message
	Console.println("Introduce the area of the circle: ")

	// We need to declare or val area and use the readInt() function to let the user type the value
	val area = readInt()

	// Math operation for area over Pi
	val division = area/Math.PI

	// Math operation where we calculate the radius
	val radio = Math.sqrt(division)

## 2) Develop an algorithm in Scala that let us know if a number is a prime number

	// This function let us print on screen a message
	Console.println("Introduce the number: ")

	// We need to declare or val num and use the readInt() function to let the user type the value
	val num = readInt()

	// If conditional that evaluates if the module of the typed number equals to zero
	if(num % 2 == 0){

		// In case the module equals to zero, prints on screen that the number is not a prime number
		println("Not a prime number")

			// In case the module doesn't equals to zero, prints on screen that the number is a prime number
	}else{
		prinln("Is a prime number")
	}

# 3) Given the variable bird = "tweet", use string interpolation to print "I'm writing a tweet"

	// In this line we declare a variable that contains the text "tweet"
	val bird = "tweet"

	// We use interpolation to print the requested text on the screen
	println(s"Estoy escribiendo un $bird")

# 4) Given the variable message = "Hello Luke, I am your father!" use slice to extract the sequence "Luke"

	val message = "Hi Luke, I am your father!"

	// the stored function "slice" does the job of splitting the value from index 5 to 9, skipping 5.
	message.slice (5,9)

# 5) What is the difference between value and a variable in Scala?

	// Angeles Valadez Jonathan
	/ *
	A variable in scala can change its value if it is the case and it only receives integers, while a value is constant and depending on its value, its type can change to integer, string or any.
	* /

	// Castillo Solis Fabian Eduardo 
	/ *
	A variable can always change its value if required but it has the characteristic of only operating with integers while a value is constant, that is, it does not change and depending on the value of it, the type can be changed.
	* /

# 6) Given the tuple (2,4,5,1,2,3,3.1416,23) returns the number 3.1416

	val Numbers = (2,4,5,1,2,3,3.1416,23)

	// From the Numbers tube, we take the number in position 7
	Numbers._7