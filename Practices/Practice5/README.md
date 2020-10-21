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
>No.1- Showing our dataframe with .show()
```scala
  df.show()
```

>No.2- We can show specific columns from our datafrma without tem having 
>to truncate .orderBy() and .show(false)
```scala
df.orderBy("department","state").show(false)
```

>No.3- We can sort values by columns in our dataframe with .sort().
>The .sort() function has additional functions we can call as parameters 
>as it is shown bellow.
```scala
df.sort(col("department").asc,col("state").desc).show(false)
```
>No.4 - We can group our dataframe by a header and get the mean from it
```sacala
df.groupBy("department").mean().show()
```

>No.5 - Once grouped, we cna even count how many departments we have in this column
```scala
df.groupBy("department").count().show()
```

>No.6 - Using .max() we can met the Maximum numbers expressed within the groupd data
```scala
df.groupBy("department").max().show()
```

>No.7 - Same las the last command, we could even get the minimum numbers from the >group.
```scala
df.groupBy("department").min().show()
```

>No.8 - A quick way to sum all values in a column is to use .sum(), in this example we
>try to sum the salary
```scala
df.groupBy("salary").sum().show()
```scala

>No.9 - This dataframe has a "salary" and "bonus" column, we can create a new >dataframe with a new column for the sum of "salary" and "bonus" as "totalAmount" like >this
```scala
val newdf = df.withColumn("totalAmount",df("salary")+df("bonus"))
```

>No.10 - We can even use logical operators, say we want to filter by employees over 30 >years old
```scala
df.filter("age > 30").show
```
>Or even filter by a a specific range
```scala
df.filter($"age" > 30 && $"age" < 40).show
```

>No.11 - We can perform operations on datasets such as selecting every employee and >increasing their age by one
```scala
df.select($"employee_name", $"age" + 1).show()
```

>No.12 - with select, we could filter how many different number we have with
```scala
df.select(countDistinct("salary")).show()
```

>No.13 - We can also create a dataframe by loading data from a CSV file, for thi >example, we will be loading data from a file named CitiGroup2006_2008.csv
```scala
val df2 = spark.read.option("header", "true").option("inferSchema","true")csv("CitiGroup2006_2008")
```

>No.14 - We can use a function to remove duplicates (row based)
```scala
df2.dropDuplicates().show()
```

>No.15 - We can even work on several dataframes at the same time, say we have the >following 2 datasets
```scala
val dataframeA = Seq(
    (3,"Tijuana", "Salma",664123, 45000,"DEV"),
    (1,"Ensenada","Fernando",646123, 50000,"IT")
).toDF("emp_id","emp_city","emp_name","emp_phone","emp_sal","emp_department")

val dataframeB = Seq(
    (3,"Tijuana", "Salma",664123, 45000,"DEV"),
    (1,"Sydney","Fernando",646123, 48000,"IT")
).toDF("emp_id","emp_city","emp_name","emp_phone","emp_sal","emp_department")
```
>And we only want to observe de data that is contained in dataframeA that is not >contained in dataframeB
```scala
dataframeA.except(dataframeB).show()
```

>No.16 - In a very similary way, we could only get data in common from 2 datasets
```scala
val intersectValues = dataframeA.intersect(dataframeB)
```