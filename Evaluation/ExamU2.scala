//Exam Unit 2

import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

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

val df = dataframe.withColumn("species", when($"species"==="setosa","1").when(col("species")==="virginica","2").otherwise("3").cast("int"))

df.columns//Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species)

df.printSchema()
//|-- sepal_length: double (nullable = true)
//|-- sepal_width: double (nullable = true)
//|-- petal_length: double (nullable = true)
//|-- petal_width: double (nullable = true)
//|-- species: integer (nullable = true)

df.show(5)

//+------------+-----------+------------+-----------+-------+
//|sepal_length|sepal_width|petal_length|petal_width|species|
//+------------+-----------+------------+-----------+-------+
//|         5.1|        3.5|         1.4|        0.2|      1|
//|         4.9|        3.0|         1.4|        0.2|      1|
//|         4.7|        3.2|         1.3|        0.2|      1|
//|         4.6|        3.1|         1.5|        0.2|      1|
//|         5.0|        3.6|         1.4|        0.2|      1|
//+------------+-----------+------------+-----------+-------+
//only showing top 5 rows


df.describe().show()

//Practice 1  and 2 (error feactures)
val assembler = (new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width","petal_length", "petal_width")).setOutputCol("features"))

val features = assembler.transform(df)
features.show()
//+------------+-----------+------------+-----------+-------+-----------------+
//|sepal_length|sepal_width|petal_length|petal_width|species|         features|
//+------------+-----------+------------+-----------+-------+-----------------+
//|         5.1|        3.5|         1.4|        0.2|      1|[5.1,3.5,1.4,0.2]|
//|         4.9|        3.0|         1.4|        0.2|      1|[4.9,3.0,1.4,0.2]|
//|         4.7|        3.2|         1.3|        0.2|      1|[4.7,3.2,1.3,0.2]|
//|         4.6|        3.1|         1.5|        0.2|      1|[4.6,3.1,1.5,0.2]|

val indexer = new StringIndexer().setInputCol("species").setOutputCol("label")
val output = indexer.fit(features).transform(features)
output.show(5)

//+------------+-----------+------------+-----------+-------+-----------------+-----+
//|sepal_length|sepal_width|petal_length|petal_width|species|         features|label|
//+------------+-----------+------------+-----------+-------+-----------------+-----+
//|         5.1|        3.5|         1.4|        0.2|      1|[5.1,3.5,1.4,0.2]|  2.0|
//|         4.9|        3.0|         1.4|        0.2|      1|[4.9,3.0,1.4,0.2]|  2.0|
//|         4.7|        3.2|         1.3|        0.2|      1|[4.7,3.2,1.3,0.2]|  2.0|
//|         4.6|        3.1|         1.5|        0.2|      1|[4.6,3.1,1.5,0.2]|  2.0|
//|         5.0|        3.6|         1.4|        0.2|      1|[5.0,3.6,1.4,0.2]|  2.0|
//+------------+-----------+------------+-----------+-------+-----------------+-----+
//only showing top 5 rows


//error
val splits = output.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//error
val model = trainer.fit(train)

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
//Test set accuracy = 0.9

