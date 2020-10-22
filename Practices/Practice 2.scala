# 1) Create a list called "list" with the elements "red", "white", "black"

    val list = collection.mutable.Set ("red", "white", "black")

# 2) Add 5 more items to "list" "green", "yellow", "blue", "orange", "pearl"

    list + = "green"
    list + = "yellow"
    list + = "blue"
    list + = "orange"
    list + = "pearl"

# 3) Bring the items from "list" "green", "yellow", "blue"

    val list1 = list.slice (1,3)
    val list2 = list.slice (6,7)
    val endList = list.concat (list1, list2)

 # 4) Create an array of numbers in the range 1-1000 in steps of 5 by 5
 
    // With this method an array is created, the first parameter is the lower limit of the range
    // the second parameter is the upper limit of the range and the last parameter is the number of steps
    // in which the sequence of numbers of the array will be displayed, if this parameter is omitted by default
    // the number of steps will be done one by one
    Range (1,1000,5)

# 5) What are the unique elements of the list List (1,3,3,4,6,7,3,7) use conversion to sets

    // Create the list of integers
    val listN = List (1,2,3,4,2,3,4,99)

    // Do a convert to set with the list of integers
    val set = listN.toSet

    // Print the set
    set

# 6) Create a mutable map named names that contains the following "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

    val MapaMutable = collection.mutable.Map (("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))

# 6 A) Print all the keys on the map

    MapMutable.keys

# 7 B) Add the following value to the map ("Miguel", 23)

    MapMutable + = ("Miguel" -> 23)