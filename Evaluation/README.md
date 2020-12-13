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
df.columns
```

### 3- How is the squema?
```scala
df.printSchema()
```

### 4- Print the first 5 columns:
```scala
df.show(5)
```

### 5- Use the describe() method to learn about the data on the dataframe:
```scala
df.describe().show()
```

### 6- Perform the corresponding transformation for the categorical data which will become the labels we'll classify.
```scala
val assembler = (new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width","petal_length", "petal_width")).setOutputCol("features"))

val output = assembler.transform(df)
```

### 7- Build the classification model and explain its architecture:
```scala
val splits = output.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

val model = trainer.fit(train)
```
### 8- Print the model's results:
```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```

### [Code Explanation Video]()