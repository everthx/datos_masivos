README file from Unit_1 Branch

# Practice 2

>In this practice the goal was to create a list 
>called "lista" with the following elements 
>"rojo", "blanco", "negro"
>**Our solution was the following:**
```scala
var  lista =  scala.collection.mutable.MutableList ("rojo" , "blanco" , "negro" )
```

>The second goal was to add 5 more elements 
>to the previous list, the elements to add are 
>"lista", "verde" ,"amarillo", "azul", "naranja", "perla"
>**Our solution was the following:**
```scala
lista + = ("verde","amarillo","azul","naranja","perla")
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