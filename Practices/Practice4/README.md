# Practice 4

In this practice, 5 fibonacci algorithms were performed in Scala, they were obtained from the following page:

https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci

>**1st Algorithm :** Descending recursive version

In the first algorithm,  the parameter variable is defined as an integer, then there is the "if" condition which evaluates if the number that was passed is less than 2, if so, the number is returned, if not then it adds it  until the result is found.

```scala
def fibonacci(n: Int): BigInt = {
    if(n<2)
    {
        return n
    }
    else
        {
            return fibonacci (n-1) + fibonacci (n-2)
        }//scala> fibonacci(10) 
 //res8: BigInt = 55
```
>**2nd Algorithm:** Version with explicit formula

This algorithm is similar as the first one, but instead of adding the number what it does is that it goes through a formula, the first one is varphi with a value equals to 1.618033988749895, then it goes to the following formula and sends the result with the rounding function and returns an integer from it.

```scala
import scala.math.sqrt
def fibonacci(n: Int): BigInt = {
    if(n<2)
    {
        return n
    }
    else
    {
        var varphi = (1+sqrt(5))/2
        var j=((math.pow(varphi,n))-(1-varphi))/sqrt(5)
        return Math.round(j) 
    }
    }//scala> fibonacci(11)
    //res14: BigInt = 89
```

>**3rd Algorithm :**  Iterative version

In this algorithm 3 integer variables are declared, then there is "for" statement that loops from 0 to the value passed in the parameter, returning the new value of a after each iteration.
For example, if you enter 10, it returns a string of numbers that are obtained in each cycle, those numbers would be: 1 1 2 3 5 8 13 21 34 55.

```scala
def fibonacci3(n: Int){
    var a:Int =0
    var b:Int =1
    var c:Int =0

    for(k:Int <- Range(0,n))
    {
        c = b + a 
        a=b
        b=c

        print(s"$a ")
    }
}
```
>**4th Algorithm :**   Iterative version 2 variables

This algorithm is similar to the previous one, but this uses with only 2 variables, both are declared with a value, then there is the a "for" loop with a range from 0 to the value given as a parameter, the operations are carried out, only in this case it's faster since there are only two operations and a print.
For example, if we write 11 it prints the numbers of the result of the two operations as a string until the for loop ends: 1 1 2 3 5 8 13 21 34 55 89.

```scala
def fibonacci4(n: Int){
    var a:Int =0
    var b:Int =1

    for(k:Int <- Range(0,n))
    {
        b = b + a
        a = b - a

        print(s"$a ")
    }
}
```
>**5th Algorithm :** Iterative version vector

This algorithm it is more complex than the others but the same as seen in Wikipedia, first, the condition is defined if the number less than 2 it prints the number but if not, the number enters a "for" loop, an array is declared, the first will start from 0 until the value of the number given and a 1 is added to it, in position 0 of the array this will have a value of 0, in position 1 of the array it will have a value of 1, once this is defined it enters in the "for" loop where k is the counter that starts at 2 up to the value of the number that is given from the paramenter, the result of the operation is stored in the position where the counter of the loop ends until it reaches the number that was defined and at the end it prints the vector.

```scala
def fibonacci(n: Int) = {
    if (n<2)
    {
     println(n)
    }
    else
    {
        val vector = Array.range(0, n+1)
        vector (0) = 0
        vector (1) = 1
        for (k <- 2 to n)
        {
            vector(k) = vector(k-1) + vector(k-2)
        }
         println(vector.mkString(" "))
    }
}//fibonacci(11)
//0 1 1 2 3 5 8 13 21 34 55 89
```