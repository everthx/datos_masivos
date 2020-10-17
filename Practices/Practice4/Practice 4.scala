//Practice 4: Implement Fibonacci Series

//1st Algorithm
def fibonacci(n: Int): BigInt = {
    if(n<2)
    {
        return n
    }
    else
        {
            return fibonacci (n-1) + fibonacci (n-2)
        }
}//scala> fibonacci(10) 
 //res8: BigInt = 55


//2nd Algorithm
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

//3rd Algorithm 3
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
}//scala> fibonacci3(5)

//4th Algorithm
def fibonacci4(n: Int){
    var a:Int =0
    var b:Int =1

    for(k:Int <- Range(0,n))
    {
        b = b + a
        a = b - a

        print(s"$a ")
    }
}//scala> fibonacci3(5)


//Algorithm 5
def fibonacci(n: Int): BigInt =
{
    if (n<2)
    {
        return n
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
        return  Math.round(vector(n))
    }
}//scala> fibonacci(10)
//res43: BigInt = 55