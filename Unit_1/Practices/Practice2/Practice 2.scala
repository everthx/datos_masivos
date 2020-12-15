//Practice 2
// 1. Create a list called "lista" with the following elements "rojo", "blanco", "negro"

val  lista =  scala.collection.mutable.MutableList ( "rojo" , "blanco" , "negro" )

// 2. Add 5 more elements to "lista", "verde" ,"amarillo", "azul", "naranja", "perla"

lista + = ("verde","amarillo","azul","naranja","perla")//lista.type = MutableList(rojo, blanco, negro, verde, amarillo, azul, naranja, perla)

// 3. Bring back the following elements from "lista", "verde", "amarillo", "azul"

lista.lift(3)//Option[String] = Some(verde)
lista.lift(4)//Option[String] = Some(amarillo)
lista.lift(5)//Option[String] = Some(azul)

// 4. Create an number array in a range from 1-100 in steps of 5 by 5.

var x= Array.range(1, 1000, 5)

// 5. Qhich are the unique elements in the list Lista(1,3,3,4,6,7,3,7), use set conversion

val lista = List(1,3,3,4,6,7,3,7)
val elementosUnicos = lista.toSet

// 6. Create a mutable map called "nombres" that contains the following
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

val mutableMap = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))

// 6 a . Print all the keys in the map

mutableMapb get "Jose"
mutableMapb get "Luis"
mutableMapb get "Ana"
mutableMapb get "Susana"

// 7 b . Add the following value to the map ("Miguel", 23)
mutableMap += ("Miguel" -> 23)