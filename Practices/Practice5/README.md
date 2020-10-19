README file from Unit_1 Branch

# Practice 5

>For this practice, we are to try out 15 predetermined functions in Spark used 
>in Dataframes.

>**Our solution was the following:**
We first must import the needed library and load the data on to a variable that will become our dataframe:
```scala
 import spark.implicits._

  val simpleData = Seq(
    ("James","Sales","NY",90000,34,10000),
    ("Michael","Sales","NY",86000,56,20000),
    ("Robert","Sales","CA",81000,30,23000),
    ("Maria","Finance","CA",90000,24,23000),
    ("Raman","Finance","CA",99000,40,24000),
    ("Scott","Finance","NY",83000,36,19000),
    ("Jen","Finance","NY",79000,53,15000),
    ("Jeff","Marketing","CA",80000,25,18000),
    ("Kumar","Marketing","NY",91000,50,21000)
  )
```

From there we can simplify the data by filtering what we want and storing it in a new variable:
```scala
  val df = simpleData.toDF("employee_name","department","state","salary","age","bonus")
```
>1- Showing our dataframe with .show()
```scala
  df.show()
```

>2- We can order data in our dataframe with .orderBy()
```scala
df.orderBy("department","state").show(false)
```

>3- We can sort values by columns in our dataframe with .sort().
>The .sort() function has additional functions we can call as parameters 
>as it is shown bellow.
```scala
df.sort(col("department").asc,col("state").desc).show(false)
```