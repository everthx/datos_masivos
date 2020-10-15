// Practice 3
// Analyse the following code with your own words

//ANALYSIS # 1
//In the present code we try to see which are the odd numbers and which are the pairs of two lists.
//The "listEvens" function is declared where the list will be entered, then there is "for" since it is a string of numbers that has
//what to enter to say which are pairs and which are not, then inside the "for" is the conditional, if the number is the residual of the
// division is 0 then it is odd, if not then it is even.
def listEvens(list:List[Int]): String ={
    for(n <- list){
        if(n%2==0){
            println(s"$n is even")
        }else{
            println(s"$n is odd")
        }
    }
    return "Done"
}
//the lists are declared and we send them to "listEvens", which will return the string with the number
// and the text if it is even or odd
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)

//3 7 afortunado

//scala> listEvens(l2)
//4 is even
//3 is odd
//22 is even
//55 is odd
//7 is odd
//8 is even
//res1: String = Done

// ANALYSIS # 2
// This code deals with a list of numbers that when one is 7 will add 14 instead of 7 and if it is another
// number other than 7 will add the value of that number.
// First the "lucky" function that will make a list of integers is declared, the variable "res" is declared
// that will carry the sum, then there is the "for" loop so that each number in the list enters, at the end is the conditional
// if it is the number equal to 7 then it will be the sum plus 14, if not it will be the sum plus the number from the list and at the end of the
// loop returns "res".

def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}
// Declare the list
val af= List(1,7,7)
println(afortunado(af))

//scala> println(afortunado(af))
//29

//ANALISIS 3

def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)
//ANALISIS 4
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}

val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))