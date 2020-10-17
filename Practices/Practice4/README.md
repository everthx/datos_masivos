
# Practice 4

In this practice, 5 fibonacci algorithms were performed in scala, they were obtained from the following page:

https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci

>**1st Algorithm :** Descending recursive version

In the first algorithm the variable is defined which will be an integer type, then there is the conditional which if the number that was defined is less than 2 then the number is returned and if it is greater than 2 then it adds until the result is found.

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

In this algorithm it is almost the same as the first one but instead of adding what it does is that it is done with its formula, the first one is varphi that its value is 1.618033988749895, then it goes to the following formula and returns the result with the rounding function and return an integer.

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

In this algorithm 3 variables of integer type are declared, then there is the for loop with a range from 0 to the value of the number that enters, doing the operations returns the value of the operations each cycle until the end of the loop.
For example, if you enter 10, it gives you the string of numbers that are obtained in each cycle that would be: 1 1 2 3 5 8 13 21 34 55.

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

In this algorithm it is similar to the previous one, only that it is with only 2 variables, both are declared, then there is the for loop with a range from 0 to the value of the number that enters, the operations are carried out, only in this case it is more fast since there are only two operations and it prints the numbers.
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

In this algorithm it is more difficult than the others but it is the same as it is put in wikipedia, first the condition is defined if it is the number less than 2 it prints the number but if it does not enter a for loop, an array is declared first which It will start from 0 until the value of the number that enters and 1 is added to it, in element 0 of the array this will have a value of 0, in element 1 of the array it will have a value of 1, once this is defined it enters In the for loop where k is the counter that starts at 2 up to the value of the number that is defined, the result of the operation is stored in the position where the counter of the loop ends until it reaches the number that was defined and at the end it prints the vector.

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