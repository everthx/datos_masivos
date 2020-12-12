//Exam Unit 2

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

var df = dataframe.distinct()

df = df.withColumn("speciesNumber", when($"species"==="setosa","1").when(col("species")==="virginica","2").otherwise("3"))

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator


dataframe.columns//Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species)

dataframe.printSchema()
 //|-- sepal_length: double (nullable = true)
 //|-- sepal_width: double (nullable = true)
 //|-- petal_length: double (nullable = true)
 //|-- petal_width: double (nullable = true)
 //|-- species: string (nullable = true)

dataframe.show(5)

//+------------+-----------+------------+-----------+-------+
//|sepal_length|sepal_width|petal_length|petal_width|species|
//+------------+-----------+------------+-----------+-------+
//|         5.1|        3.5|         1.4|        0.2| setosa|
//|         4.9|        3.0|         1.4|        0.2| setosa|
//|         4.7|        3.2|         1.3|        0.2| setosa|
//|         4.6|        3.1|         1.5|        0.2| setosa|
//|         5.0|        3.6|         1.4|        0.2| setosa|
//+------------+-----------+------------+-----------+-------+
//only showing top 5 rows

dataframe.describe().show()

val splits = dataframe.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//error Practice 1  and 2
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val assembler = (new VectorAssembler().setInputCols(Array("", "")).setOutputCol("features"))


val model = trainer.fit(train)

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

