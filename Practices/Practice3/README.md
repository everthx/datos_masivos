
# Practice 3

In the present practice, an analysis of the following codes was carried out:

>**Analisys # 1 :**<
>In the present code we try to see which are the odd numbers and which are the pairs of two lists.<

The "listEvens" function is declared where the list will be entered, then there is "for" since it is a string of numbers that has what to enter to say which are pairs and which are not, then inside the "for" is the conditional, if the number is the residual of the division is 0 then it is odd, if not then it is even.
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
>the lists are declared and we send them to "listEvens", which will return the string with the number and the text if it is even or odd
```scala
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)
```
