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

//No.1
  df.show()

//No.2
df.orderBy("department","state").show(false)

//No.3
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

//No.9 - with select, we could filter how many different number we have with
df.select(countDistinct("salary")).show()

//No.10 - 

//No.11

//No.12

//No.13

//No.14

//No.15
