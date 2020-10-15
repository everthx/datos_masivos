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
// First the "afortunado" function that will make a list of integers is declared, the variable "res" is declared
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

// ANALYSIS 3
// In this code it is observed if there is a balance in the sum of the numbers in the list.
// First the "balance" function is declared where the list will enter and return the boolean value, they are declared
// the primera and segunda variables that start with "0", where "segunda" is the sum of the values ​​in the list
// then there is the "for" loop that what it does is that in the first variable add the value of the list plus the position it is in
// the counter of the "for" cycle and in the second the sum of the entire list is subtracted from the number of the list, then there is the conditional
// that compares the result of both variables, if they are the same, it shows "true", if it isn't, it exits the "for" loop and shows "false".

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

balance(bl)//res5: Boolean = true
balance(bl2)//res6: Boolean = true
balance(bl3)//res7: Boolean = false

// ANALYSIS 4
// This code returns true or false if the word is a palindrome.
// First define the "palindromo" function that will enter the word and return boolean,
// then analyze the word with the "reverse" function, checking if the word entered is the same
// to the word that is formed backwards.
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
// declaration of variables
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))//true
println(palindromo(palabra2))//true
println(palindromo(palabra3))//false