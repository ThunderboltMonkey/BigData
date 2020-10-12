// PUNTO 1)
// Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
val lista = collection.mutable.Set("rojo","blanco","negro")

// PUNTO 2)
// Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
lista += "verde"
lista += "amarillo"
lista += "azul"
lista += "naranja"
lista += "perla"

// PUNTO 3)
// Traer los elementos de "lista" "verde", "amarillo", "azul"
val lista1 = lista.slice(1,3)
val lista2 = lista.slice(6,7)
val listaFinal = list.concat(lista1, lista2)

// PUNTO 4)
// Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
// Con este método se crea un arreglo, el primer parámetro es el límite inferior del rango
// el segundo parámetro es el límite superior del rango y el último parámetro es el número de pasos
// en que se mostrará la secuencia de números del arreglo, si se omite este parámetro por default 
// el número de pasos se hará de uno en uno
Range(1,1000,5)

// PUNTO 5) 
//Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos

//Creo la lista de enteros
val listN = List(1,2,3,4,2,3,4,99)

//Hago un convert a conjunto con la lista de enteros 
val conjunto = listaN.toSet

//Imprimo el conjunto
conjunto

// PUNTO 6) 
//  Crea una mapa mutable llamado nombres que contenga los siguiente
//  "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

val MapaMutable = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))

// PUNTO 6 a) 
//  Imprime todas la llaves del mapa

MapaMutable.keys

// PUNTO 7 b) 
//  Agrega el siguiente valor al mapa("Miguel", 23)

MapaMutable += ("Miguel" -> 23)
