
# Practice 4

In this practice, 5 fibonacci algorithms were performed in scala, they were obtained from the following page:

https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci

>**1st Algorithm :** Descending recursive version

```scala
def fibonacci(n: Int): BigInt = {
    if(n<2)
    {
        return n
    }
    else
        {
            return fibonacci (n-1) + fibonacci (n-2)
        }
```
>**2nd Algorithm:** 

First the "afortunado" function that will make a list of integers is declared, the variable "res" is declared that will carry the sum, then there is the "for" loop so that each number in the list enters, at the end is the conditional if it is the number equal to 7 then it will be the sum plus 14, if not it will be the sum plus the number from the list and at the end of the loop returns "res".

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
    }

```

>**3rd Algorithm :** 

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
>**4th Algorithm :**  

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
>**5th Algorithm :**  

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
}
```