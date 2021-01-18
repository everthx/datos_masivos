// Final Proyect Unit 4

//Made by
//Hernández Negrete Salma Fabel - 16212354
//Luna Fuentes Fernando - 16210975

//Objective: Performance comparison on the following machine learning algorithms
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

import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics

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

//Transform categorical data to numeric
//StringIndexer encodes a string column of labels to a column of label indices.
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

//Let the assembler object converts the input values ​​to a vector. 
//Use the VectorAssembler object to convert the input columns of the df to a single 
//output column of an array called "features", set the input columns from where we are supposed to 
//read the values.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df).select($"label", $"features")

output.show(5)
//+-----+--------------------+
//|label|            features|
//+-----+--------------------+
//|  0.0|[2143.0,5.0,261.0...|
//|  0.0|[29.0,5.0,151.0,1...|
//|  0.0|[2.0,5.0,76.0,1.0...|
//|  0.0|[1506.0,5.0,92.0,...|
//|  0.0|[1.0,5.0,198.0,1....|
//+-----+--------------------+

////Separates the characteristics into 70% training and 30% testing
val Array(training, test) = output.randomSplit(Array(0.7, 0.3), seed = 12345)

val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

//Fit the model
val lsvcModel = lsvc.fit(training)

val results = lsvcModel.transform(test)

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

// Print the Interception coeficient
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
//Coefficients: [4.339356943245717E-6,-0.004343870375279081,5.765546723075568E-4,-0.07211029685388683,2.5540225773264664E-4,0.007528323053442825] 
//Intercept: -1.07258737561311

println("Accurancy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")
//Accurancy: 0.8849789152246619
//Test Error = 0.11502108477533812

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Decision Three<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

// Importing the libraries is required in order to get the example done.
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

//Transform categorical data to numeric
//StringIndexer encodes a string column of labels to a column of label indices.
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

//Let the assembler object converts the input values ​​to a vector. 
//Use the VectorAssembler object to convert the input columns of the df to a single 
//output column of an array called "features", set the input columns from where we are supposed to 
//read the values.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df)


// Index labels, adding metadata to the label column.
// Fit on whole dataset to include all labels in index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(output)

// Automatically identify categorical features, and index them.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(output)

//Separates the characteristics into 70% training and 30% testing
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

// Show by stages the classification of the tree model
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

//import libraries
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

//Transform categorical data to numeric
//StringIndexer encodes a string column of labels to a column of label indices.
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val output = stringindexer.fit(dataframe).transform(dataframe)

//Let the assembler object converts the input values ​​to a vector. 
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")

//Separates the characteristics into 70% training and 30% testing
val Array(training, test) = output.randomSplit(Array(0.7, 0.3), seed = 12345)

// Fit the model
val lr = new LogisticRegression()

// Create a new pipeline object with the following elements: assembler, lr.
val pipeline = new Pipeline().setStages(Array(assembler, lr))

// Adjust (fit) the pipeline for the training set.
val model = pipeline.fit(training)
val results = model.transform(test)

// Convert the test results (test) on RDD using .as and .rdd
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

// Print the Confusion matrix
println("Confusion matrix:")
println(metrics.confusionMatrix)
//Confusion matrix:
//11969.0  191.0  
//1306.0   288.0

println("Accurancy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")
//Accurancy: 0.891158935582376
//Test Error = 0.10884106441762398

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Multilayer Perceptron<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//import libraries
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
val dataframe = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Transform categorical data to numeric
//StringIndexer encodes a string column of labels to a column of label indices.
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)

//Let the assembler object converts the input values ​​to a vector. 
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df)

//Split the data into 70% for training and 30% for tests
val split = output.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = split(0)
val test = split(1)

// specify layers for the neural network:
// input layer of size 6 (features), two intermediate of size 4 and 1
// and output of size 2 (classes)
val layers = Array[Int](6, 4, 1, 2)
output.show()

// create the trainer and set its parameters
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// train the model
val model = trainer.fit(train)

// compute accuracy on the test set
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
println(s"Test Error = ${(1.0 - metrics.accuracy)}")
//Test set accuracy = 0.8827505142521305
//Test Error = 0.10884106441762398