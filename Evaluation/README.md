# Datos Masivos: Unit 2 Evaluation
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**

### 1- Load a dataframe (Iris.csv) that you'll find [Here](https://github.com/jcromerohdz/iris), clean up the data in order to be used by the following algorithm. (Important: this cleanup must be done by a Spark Scala script).
```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

var df = dataframe.distinct()

df = df.withColumn("speciesNumber", when($"species"==="setosa","1").when(col("species")==="virginica","2").otherwise("3"))
```

### a. Use Spark's Mllib library corresponding to the Multilayer Perceptron.
```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

### 2- What are the names of the columns?
```scala
dataframe.columns
```

### 3- How is the squema?
```scala
dataframe.printSchema()
```

### 4- Print the first 5 columns:
```scala
dataframe.show(5)
```

### 5- Use the describe() method to learn about the data on the dataframe:
```scala
dataframe.describe().show()
```

### 6- Perform the corresponding transformation for the categorical data which will become the labels we'll classify.
```scala
//TODO("P7")
```

### 7- Build the classification model and explain its architecture:
```scala

```
### 8- Print the model's results:
```scala

```

### [Code Explanation Video]()