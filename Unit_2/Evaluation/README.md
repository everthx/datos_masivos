# Datos Masivos: Unit 2 Evaluation
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**

import required libraries

```scala
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

Start a simple Spark Session
```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

```

We load the data to be our data frame
```scala
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

```

Print the squema
```scala
dataframe.printSchema()
 //|-- sepal_length: double (nullable = true)
 //|-- sepal_width: double (nullable = true)
 //|-- petal_length: double (nullable = true)
 //|-- petal_width: double (nullable = true)
 //|-- species: string (nullable = true)

```

Encodes a column of tag strings to a column of tag indexes. By default, this is sorted by tag frequencies, so the most frequent tag gets index 0.
```scala
val  stringindexer = new StringIndexer().setInputCol("species").setOutputCol("label")
val df =  stringindexer.fit(dataframe).transform(dataframe)
```

Column names
```scala
df.columns// Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species, label)
```

Dataframe schema
```scala
df.printSchema()
 //|-- sepal_length: double (nullable = true)
// |-- sepal_width: double (nullable = true)
 //|-- petal_length: double (nullable = true)
// |-- petal_width: double (nullable = true)
// |-- species: string (nullable = true)
// |-- label: double (nullable = false)
```

The first 5 pieces of data in the dataframe
```scala
df.show(5)
//+------------+-----------+------------+-----------+-------+-----+
//|sepal_length|sepal_width|petal_length|petal_width|species|label|
//+------------+-----------+------------+-----------+-------+-----+
//|         5.1|        3.5|         1.4|        0.2| setosa|  2.0|
//|         4.9|        3.0|         1.4|        0.2| setosa|  2.0|
//|         4.7|        3.2|         1.3|        0.2| setosa|  2.0|
//|         4.6|        3.1|         1.5|        0.2| setosa|  2.0|
//|         5.0|        3.6|         1.4|        0.2| setosa|  2.0|
//+------------+-----------+------------+-----------+-------+-----+
```

Describe () method to learn about data
```scala
df.describe().show()
```

Let the assembler object converts the input values ​​to a vector. Use the VectorAssembler object to convert the input columns of the df to a single output column of an array called "features". The columns "sepal_length", "sepal_width", "petal_length", "petal_width" are taken.
Then assembler is used to transform our DataFrame into two columns: label and characteristics.
```scala
val assembler = (new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width","petal_length", "petal_width")).setOutputCol("features"))

val output = assembler.transform(df).select($"label", $"features")
output.show(5)

//+-----+-----------------+
//|label|         features|
//+-----+-----------------+
//|  2.0|[5.1,3.5,1.4,0.2]|
//|  2.0|[4.9,3.0,1.4,0.2]|
//|  2.0|[4.7,3.2,1.3,0.2]|
//|  2.0|[4.6,3.1,1.5,0.2]|
//|  2.0|[5.0,3.6,1.4,0.2]|
//+-----+-----------------+
```

### Multilayer perceptron classifier

We have the test and training data, where you separate the data into 70 for training and 30 for testing.
```scala
val splits = output.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)
```

We have "layers" with a size of 4 (characteristics), two intermediate ones of size 5 and 4 and output of size 3 (classes).
```scala
val layers = Array[Int](4, 5, 4, 3)
```

We have a “trainer” that the coach creates and establishes its parameters.
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

Now we create the model with the trainer and training data.
```scala
val model = trainer.fit(train)
```

Calculate the precision on the test equipment.
```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

Print the results
```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
////Test set accuracy = 0.925
```

### [Code Explanation Video]()