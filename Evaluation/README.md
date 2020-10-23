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
df.select($"Date",$"Open",$"High",$"Low",$"Close").show()
```

### 6 - Use .describre() to learn about the Dataframe:
```scala

```

### 7 - Create a new dataframe with a column named "HV Ratio" which is the relation between the price on column "High" against column "Volume" of actions negotiated in a day. (Hint: Its an operation between columns)
```scala

```

### 8 - Which day did the "Close" columns had its highest peak?
```scala

```

### 9 - Write with your own words in a code comment, What is the meaning of the "Close" Column?
```scala

```

### 10 - What is the max and min of the "Volume" column?
```scala

```

### 11 - With Scala/Spark $ syntax answer the following:
#### a. How many days was the "Close" columns bellow $600?
#### b. What time percentage was the "High" column greater than $500
#### c. What is the Pearson Correlation between columns "High" and "Volume"?
#### d. What is the per year max of the "High" column?
#### e. What is the average of the "Close" column per monthin the calendar?
```scala

```