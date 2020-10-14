//Practica 4 Implementar series Fibonacci

//Algorithm 1
import scala.math.sqrt

def fibonacci(n: Int): BigInt =
{
if(n<2){
return n
}
else{
return fibonacci (n-1) + fibonacci (n-2)
}
}//scala> fibonacci(10) 
 //res8: BigInt = 55


//Algorithm 2
def fibonacci(n: Double): Double = {
if(n<2){
return n
}
else{
var varphi = ((1+sqrt(5))/2)
var j=((math.pow(varphi,n))-(1-varphi))/sqrt(5)
return (j) 
}
}//scala> fibonacci(11)
//res14: Double = 89.27414595449586

//Algorithm 3
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

//Algorithm 4
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


//Algorithm 5