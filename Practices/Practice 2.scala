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
