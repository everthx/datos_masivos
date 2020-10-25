# Big Data:
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**


### 1 - Start a simple spark session::::
```console
spark-shell
```

### 2 - Load the Netflix Stock SVC file, make spark infer the data types:
```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix.csv")
```

### 3 - What are the names of the columns?
```scala
df.columns
```

### 4 - What is the name of the squema?
```scala
df.printSchema()
```

### 5 - Print the first 5 columns:
```scala
df.head(5) //Prints 5 rows
df.select($"Date",$"Open",$"High",$"Low",$"Close").show() //Prints 5 columns
```

### 6 - Use .describre() to learn about the Dataframe:
```scala
df.describe().show()
```

### 7 - Create a new dataframe with a column named "HV Ratio" which is the relation between the price on column "High" against column "Volume" of actions negotiated in a day. (Hint: Its an operation between columns)
```scala
val dfnew = df.withColumn("HV Ratio", df("High")/df("Volume")).show()
```

### 8 - Which day did the "Close" columns had its highest peak?
```scala
df.orderBy($"Close".desc).show(1)
```

### 9 - Write with your own words in a code comment, What is the meaning of the "Close" Column?
```scala
/*Close is the average relation of exchange between High and Low that Netflix close don that day*/
```

### 10 - What is the max and min of the "Volume" column?
```scala
df.select(max("Volume")).show()
df.select(min("Volume")).show()
```

### 11 - With Scala/Spark $ syntax answer the following:
>#### a. How many days was the "Close" columns bellow $600?
```scala
df.filter($"Close"<600).count()
```

>#### b. What time percentage was the "High" column greater than $500
```scala
var hdf = (df.filter($"High">500).count()*1.0/df.count())*100
```

>#### c. What is the Pearson Correlation between columns "High" and "Volume"?
```scala
df.stat.corr("High","Volume")
```

>#### d. What is the per year max of the "High" column?
```scala
df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()
```

>#### e. What is the average of the "Close" column per month in the calendar?
```scala
df.groupBy(month(df("Date")).alias("Months")).max("Close").sort(asc("Months")).show()
```