# Big Data: Datos Masivos Unit 1 Exam
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**


### 1 - Start a simple spark session:
>To start a session we open a Terminal or CMD window and type the following.
```console
spark-shell
```

### 2 - Load the Netflix Stock SVC file, make spark infer the data types:
>The following lines import the needed spark library, loads the builder toi a variable and then we call the data from the CSV file including it's headers.
```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix.csv")
```

### 3 - What are the names of the columns?
>We can use .columns to show the name of the columns in our dataframe as an Array:
```scala
df.columns
```

### 4 - What is the name of the squema?
>With the .printSchema() method we can see a simple squeme of the dataframe.
```scala
df.printSchema()
```

### 5 - Print the first 5 columns:
>There are 2 ways we can achieve this, showing 5 ROWS with .show(5) or showing 5 specific COLUMNS with .select()
```scala
df.show(5) //Prints 5 rows
df.select($"Date",$"Open",$"High",$"Low",$"Close").show() //Prints 5 columns
```

### 6 - Use .describre() to learn about the Dataframe:
>To learn from the dataframe we invoke .describe() showing us statistical details.
```scala
df.describe().show()
```

### 7 - Create a new dataframe with a column named "HV Ratio" which is the relation between the price on column "High" against column "Volume" of actions negotiated in a day. (Hint: Its an operation between columns)
>A new val is created with .withColumn specifying the name and content as the division between "High" and "Volume"
```scala
val dfnew = df.withColumn("HV Ratio", df("High")/df("Volume")).show()
```

### 8 - Which day did the "Close" columns had its highest peak?
>We orber by the "Close" columns in a descending way and show the 1st value
```scala
df.orderBy($"Close".desc).show(1)
```

### 9 - Write with your own words in a code comment, What is the meaning of the "Close" Column?
```scala
/*Close is the average relation of exchange between High and Low that Netflix close don that day*/
```

### 10 - What is the max and min of the "Volume" column?
>We can use min() and max() to show on "Volume"
```scala
df.select(max("Volume")).show()
df.select(min("Volume")).show()
```

### 11 - With Scala/Spark $ syntax answer the following:
>#### a. How many days was the "Close" columns bellow $600?
>We filter by Close when it is <600 and count the times it appeared.
```scala
df.filter($"Close"<600).count()
```

>#### b. What time percentage was the "High" column greater than $500
>We filter the amount of times "High" was over 500, multiply that by 1.0 to get a float and divide that by the amount of rows, finally we multiply that by 100 to get the percentage.
```scala
var hdf = (df.filter($"High">500).count()*1.0/df.count())*100
```

>#### c. What is the Pearson Correlation between columns "High" and "Volume"?
>Spark has an integrated function we can call as stat.corr().
```scala
df.stat.corr("High","Volume")
```

>#### d. What is the per year max of the "High" column?
>We group by year, the it's max and sort that by the years
```scala
df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()
```

>#### e. What is the average of the "Close" column per month in the calendar?
>We group by months, get the mean values from that and sort it by the months.
```scala
df.groupBy(month(df("Date")).alias("Months")).mean("Close").sort(asc("Months")).show()
```