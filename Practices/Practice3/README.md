
# Practice 3

In the current practice, an analysis of the following codes was carried out:

>**Analisys # 1 :** In the present code we try to see which are the odd numbers and which are the pairs of two lists.

The "listEvens" function is declared where the list will be entered, then there is a "for" loop statement since it is a string of numbers that has what to enter to say which are pairs and which are not, then inside the "for" loop statement we find a condition, if the mod of the number equals 0 then that number is an even number, otherwise we have an odd number.
```scala
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
```
the lists are declared and we pass them over to "listEvens", which will return the string with the number and text declaring if the number was even or odd.
```scala
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)
```
>**Analisys # 2 :** This code deals with a list of numbers that when one is 7 it will add 14 instead of 7 and if it's another number other than 7, it will add the value of that number.

First, the "afortunado" function that will receive a list of integers is declared, the variable "res" is declared, this will carry the sum, then there is the "for" loop so that each number in the list is evaluated, at the end there's the conditional that if it's a number equal to 7 we will add 14 to the sum, if not, it will be the sum plus the number from the list and at the end of the loop returns the result as "res".
```scala
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

val af= List(1,7,7)
println(afortunado(af))
```

>**Analisys # 3 :** In this code it is observed if there is a balance in the sum of the numbers in the list.

First, the "balance" function is declared, this is where the list will enter and return a boolean value. De declare two variables as "primera" and "segunda" with a value of 0, where "segunda" is the sum of the values ​​in the list, then there's the "for" loop statement that goes thru the list asigning the value in the given position to the declared variables, then there is an "if" condition that compares the result of both variables, if they are the same, it returns "true", if they aren't, it exits the "for" loop and returns "false".

```scala
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

balance(bl)//Boolean = true
balance(bl2)//Boolean = true
balance(bl3)//Boolean = false
```
>**Analisys # 4 :**  This code returns true or false if the word is a palindrome.

First, we define the "palindromo" function that will receive a word and return a boolean, it then analyzes the word with the "reverse" internal function, checking if the word entered is the same as the word that is formed backwards.

```scala
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))//true
println(palindromo(palabra2))//true
println(palindromo(palabra3))//false
```