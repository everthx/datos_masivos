// Practice 1
//1. Develop and algorythm in Scala that can calculate a circle's radius

val a=17.27
val radio= a/(2*3.1416)

//2. Develop an algorythm in Scala that tells me if a number is prime

def numero_primo(n :Int) : Boolean = {
    if (n <= 1)
    false
    else if (n==2)
    true
    else
    !(2 to (n-1)).exists(x=> n % x==0)
}
// Second form
def numero_primo(n: Int): Boolean = ! ((2 until n-1) exists (n % _ == 0))
// numero_primo(6)

//3. Given the variable bird = "tweet", use string interpolation to
//   print "Estoy ecribiendo un tweet"

var bird = "tweet"
println ("Estoy escribiendo un " + bird)

//4. Given the variable mensaje = "Hola Luke yo soy tu padre!", use slilce to extract the
//   "Luke" sequence

var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,9)

//5. What is the difference between value and variable in Scala?

val valor = "Unmutable value, cannot be modified"
var variable = "Mutable value, can be changed"

//6. Given the tuple (2,4,5,1,2,3,3.1416,23), return the number 3.1416 

val myTuple = (2,4,5,1,2,3,3.1416,23)
myTuple._7