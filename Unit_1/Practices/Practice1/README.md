# Practice 1

>In this practice the goal was to code in Scala an 
>algorythm to calculare an circle's radius. 
>**Our solution was the following:**
```scala
val a=17.27
val radio= a/(2*3.1416)
```

>The second goal was to code an algorythm
>that can determine if a numer is prime.
>**Our solution was the following:**
```scala
def numero_primo(n :Int) : Boolean = {
    if (n <= 1)
    false
    else if (n==2)
    true
    else
    !(2 to (n-1)).exists(x=> n % x==0)
}
```

>The third goal was that given a variable 
>bird = "tweet", use interpolation to print 
>"Estoy ecribiendo un tweet".
>**Our solution was the following:**
```scala
var bird = "tweet"
println ("Estoy escribiendo un " + bird)
```

>The forth goal was that given a variable 
>mensaje = "Hola Luke yo soy tu padre!", 
>use the slice function to extract "Luke".
>**Our solution was the following:**
```scala
var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,9)
```

>The fift goal was to provide the difference 
>between var and val.
>**Our response was the following:**
```scala
val valor = "Unmutable value, cannot be modified"
var variable = "Mutable value, can be changed"
```

>The sixth and final goal was that given a tuple of 
>(2,4,5,1,2,3,3.1416,23) to return 3.1416 from ir.
>**Our solution was the following:**
```scala
val myTuple = (2,4,5,1,2,3,3.1416,23)
myTuple._7
```