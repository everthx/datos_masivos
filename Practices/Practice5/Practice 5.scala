//Practice 5
//Practice with 15 Spark Default Functions for Dataframes, with the link referring to this topic

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
  val df = simpleData.toDF("employee_name","department","state","salary","age","bonus")

//No.1 - We can show our dataframe
  df.show()

//No.2 - We can show specific columns from our datafrma without tem having to truncate
df.orderBy("department","state").show(false)

//No.3 - We can sort values by columns in our dataframe with .sort().
//The .sort() function has additional functions we can call as parameters 
//as it is shown bellow.
df.sort(col("department").asc,col("state").desc).show(false)

//No.4 - We can group our dataframe by a header and get the mean from it
df.groupBy("department").mean().show()

//No.5 - Once grouped, we cna even count how many departments we have in this column
df.groupBy("department").count().show()

//No.6 - Using .max() we can met the Maximum numbers expressed within the groupd data
df.groupBy("department").max().show()

//No.7 - Same las the last command, we could even get the minimum numbers from the group.
df.groupBy("department").min().show()

//No.8 - A quick way to sum all values in a column is to use .sum(), in this example we
//try to sum the salary
df.groupBy("salary").sum().show()

//No.9 - This dataframe has a "salary" and "bonus" column, we can create a new dataframe with a new
//column for the sum of "salary" and "bonus" as "totalAmount" like this
val newdf = df.withColumn("totalAmount",df("salary")+df("bonus"))

//No.10 - We can even use logical operators, say we want to filter by employees over 30 years old
df.filter("age > 30").show
//Or even filter by a a specific range 
df.filter($"age" > 30 && $"age" < 40).show

//No.11 - We can perform operations on datasets such as selecting every employee and increasing their age by one
df.select($"employee_name", $"age" + 1).show()

//No.12 - with select, we could filter how many different number we have with
df.select(countDistinct("salary")).show()


//No.13 - We can also create a dataframe by loading data from a CSV file, for thi example, we will be
//loading data from a file named CitiGroup2006_2008.csv
val df2 = spark.read.option("header", "true").option("inferSchema","true")csv("CitiGroup2006_2008") //Finantial Crisis

//No.14 - We can use a function to remove duplicates (row based)
df2.dropDuplicates().show()

//No.15 - We can even work on several dataframes at the same time, say we have the following 2 datasets
val dataframeA = Seq(
    (3,"Tijuana", "Salma",664123, 45000,"DEV"),
    (1,"Ensenada","Fernando",646123, 50000,"IT")
).toDF("emp_id","emp_city","emp_name","emp_phone","emp_sal","emp_department")

val dataframeB = Seq(
    (3,"Tijuana", "Salma",664123, 45000,"DEV"),
    (1,"Sydney","Fernando",646123, 48000,"IT")
).toDF("emp_id","emp_city","emp_name","emp_phone","emp_sal","emp_department")

// and we only want to observe de data that is contained in dataframeA that is not contained in dataframeB
dataframeA.except(dataframeB).show()

//No.16 - In a very similary way, we could only get data in common from 2 datasets
val intersectValues = dataframeA.intersect(dataframeB)
