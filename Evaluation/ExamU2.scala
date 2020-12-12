//Exam Unit 2

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

var df = dataframe.distinct()

df = df.withColumn("speciesNumber", when($"species"==="setosa","1").when(col("species")==="virginica","2").otherwise("3"))

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator


dataframe.columns

dataframe.printSchema()

dataframe.show(5)

dataframe.describe().show()

val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)


val model = trainer.fit(train)

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

