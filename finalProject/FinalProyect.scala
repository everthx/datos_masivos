// Final Proyect Unit 4

//Made by
//Hernández Negrete Salma Fabel - 16212354
//Luna Fuentes Fernando - 16210975

//Objective: Comparison of the performance following machine learning algorithms
// - SVM
// - Decision Three
// - Logistic regression
// - Multilayer Perceptron


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SVM<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//import libraries
import org.apache.spark.ml.classification.LinearSVC
//Minimize errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
//
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

//Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Our Dataset is loaded into a Dataframe
val dataframe = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

dataframe.printSchema()
//|-- age: integer (nullable = true)
//|-- job: string (nullable = true)
//|-- marital: string (nullable = true)
//|-- education: string (nullable = true)
//|-- default: string (nullable = true)
//|-- balance: integer (nullable = true)
//|-- housing: string (nullable = true)
//|-- loan: string (nullable = true)
//|-- contact: string (nullable = true)
//|-- day: integer (nullable = true)
//|-- month: string (nullable = true)
//|-- duration: integer (nullable = true)
//|-- campaign: integer (nullable = true)
//|-- pdays: integer (nullable = true)
//|-- previous: integer (nullable = true)
//|-- poutcome: string (nullable = true)
//|-- y: string (nullable = true)

//
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df)

val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

//Fit the model
val lsvcModel = lsvc.fit(output)

// Imprimimos El Coeficiente De Intercepcion
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
//Coefficients: [6.330591568036637E-6,0.0,2.808166581075016E-4,-0.028961566246757903,1.2076830519916415E-4,0.004777588414066137] 
//Intercept: -1.1480593155519985

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Decision Three<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

// Importing this libraries is required in order to get the example done.
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

//Minimize errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Our Dataset is loaded into a Dataframe
val dataframe = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df)


// Index labels, adding metadata to the label column.
// Fit on whole dataset to include all labels in index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(output)

// Automatically identify categorical features, and index them.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(output)

// Split the data into training and test sets (30% held out for testing).
val Array(trainingData, testData) = output.randomSplit(Array(0.7, 0.3))

// Train a DecisionTree model.
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Convert indexed labels back to original labels.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Chain indexers and tree in a Pipeline.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

// Train model. This also runs the indexers.
val model = pipeline.fit(trainingData)

// Make predictions.
val predictions = model.transform(testData)

// Select example rows to display.
predictions.select("predictedLabel", "label", "features").show(5)

//+--------------+-----+--------------------+
//|predictedLabel|label|            features|
//+--------------+-----+--------------------+
//|           0.0|  1.0|[608.0,12.0,267.0...|
//|           0.0|  1.0|[108.0,10.0,167.0...|
//|           0.0|  1.0|[103.0,10.0,104.0...|
//|           0.0|  0.0|[291.0,5.0,291.0,...|
//|           0.0|  0.0|[626.0,15.0,117.0...|
//+--------------+-----+--------------------+

// Select (prediction, true label) and compute test error.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
//accuracy: Double = 0.8924755120213713
//Test Error = 0.10752448797862868

val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
//Learned classification tree model:
//DecisionTreeClassificationModel (uid=dtc_afeb8d936788) of depth 5 with 37 nodes
// If (feature 2 <= 486.5)
//  If (feature 4 <= 9.5)
//   If (feature 2 <= 204.5)
//     Predict: 0.0
//    Else (feature 2 > 204.5)
//     If (feature 1 <= 1.5)
//      If (feature 2 <= 290.5)
//       Predict: 0.0
//      Else (feature 2 > 290.5)
//       Predict: 1.0
//     Else (feature 1 > 1.5)
//      Predict: 0.0
//   Else (feature 4 > 9.5)
//    If (feature 2 <= 178.5)
//     Predict: 0.0
//    Else (feature 2 > 178.5)
//     If (feature 4 <= 188.5)
//      If (feature 4 <= 95.5)
//       Predict: 1.0
//      Else (feature 4 > 95.5)
//       Predict: 0.0
//     Else (feature 4 > 188.5)
//      If (feature 4 <= 498.0)
//       Predict: 0.0
//      Else (feature 4 > 498.0)
//       Predict: 1.0
//  Else (feature 2 > 486.5)
//   If (feature 2 <= 684.5)
//    If (feature 4 <= 8.5)
//     Predict: 0.0
//    Else (feature 4 > 8.5)
//     If (feature 4 <= 188.5)
//      Predict: 1.0
//     Else (feature 4 > 188.5)
//      If (feature 0 <= 4967.0)
//       Predict: 0.0
//      Else (feature 0 > 4967.0)
//       Predict: 1.0
//   Else (feature 2 > 684.5)
//    If (feature 2 <= 884.5)
//     If (feature 4 <= 3.0)
//      If (feature 1 <= 29.5)
//       Predict: 0.0
//      Else (feature 1 > 29.5)
//       Predict: 1.0
//     Else (feature 4 > 3.0)
//      Predict: 1.0
//    Else (feature 2 > 884.5)
//     If (feature 1 <= 29.5)
//      If (feature 1 <= 3.5)
//       Predict: 0.0
//      Else (feature 1 > 3.5)
//       Predict: 1.0
//     Else (feature 1 > 29.5)
//      Predict: 1.0


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Logistic regression<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

// Importing this libraries is required in order to get the example done.
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics

//Minimize errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Our Dataset is loaded into a Dataframe
val dataframe = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")


//
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val output = stringindexer.fit(dataframe).transform(dataframe)

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")

val Array(training, test) = output.randomSplit(Array(0.7, 0.3), seed = 12345)

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(assembler, lr))

val model = pipeline.fit(training)
val results = model.transform(test)

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

println("Confusion matrix:")
println(metrics.confusionMatrix)
//11969.0  191.0  
//1306.0   288.0

 metrics.accuracy
//res3: Double = 0.891158935582376

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Multilayer Perceptron<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

//Minimize errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Our Dataset is loaded into a Dataframe
val df= spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")

val output = assembler.transform(df).select($"label", $"features")

val split = output.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](5, 4, 1, 2)
output.show()

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

val model = trainer.fit(train)

val result = model.transform(test)

val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show
//+----------+-----+
//|prediction|label|
//+----------+-----+
//|       0.0|  1.0|
//|       0.0|  0.0|
//|       0.0|  1.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  1.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//|       0.0|  0.0|
//+----------+-----+
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
//Test set accuracy = 0.8829233550649208