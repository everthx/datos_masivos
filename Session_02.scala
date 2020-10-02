// Assessment 1/Practica 1
//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo

val a=17.27
val radio= a/(2*3.1416)

//2. Desarrollar un algoritmo en scala que me diga si un numero es primo
    

//3. Dada la variable bird = "tweet", utiliza interpolacion de string para
//   imprimir "Estoy ecribiendo un tweet"

var bird = "tweet"
println ("Estoy ecribiendo un " + bird)

//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la
//   secuencia "Luke"

var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,9)

//5. Cual es la diferencia entre value y una variable en scala?
//la diferencia es que variable se puede modificar (es mutable) y value es inmutable, que no se puede modificar
val valor = "Valor inmutabe/no puede ser modificado"
var variable = "Variable mutable/si puede ser modificada"

//6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416 

val myTuple = (2,4,5,1,2,3,3.1416,23)
myTuple._7