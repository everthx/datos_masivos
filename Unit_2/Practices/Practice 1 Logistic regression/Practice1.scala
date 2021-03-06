//Practice 1

//////////////////////////////////////////////
// Proyecto de regresion logistica //////////////
////////////////////////////////////////////

//  In this project we will be working with a fake advertising data set, indicating whether or not a particular internet user clicked on an Advertisement. We will try to create a model that will predict whether or not they will click on an ad based off the features of that user.
//  This data set contains the following features:
//    'Daily Time Spent on Site': consumer time on site in minutes
//    'Age': cutomer age in years
//    'Area Income': Avg. Income of geographical area of consumer
//    'Daily Internet Usage': Avg. minutes a day consumer is on the internet
//    'Ad Topic Line': Headline of the advertisement
//    'City': City of consumer
//    'Male': Whether or not consumer was male
//    'Country': Country of consumer
//    'Timestamp': Time at which consumer clicked on Ad or closed window
//    'Clicked on Ad': 0 or 1 indicated clicking on Ad

//////////////////////////////////////////////////////////
// Complete the following commented tasks ////
/////////////////////////////////////////////////////////


////////////////////////
/// Import data //////
//////////////////////

// Import a SparkSeccion with the Logistic Regression library
// Optional: Use theError reporting code
// Create a Spark Session 
// Use Spark to read the csv file "Advertising".
// Print the dataframe schema.

//Analysis:To be able to perform the logistic regression, you must first import the “logisticRegression” 
//library. After we configure the errors, then the simple spark session is created and we load our 
//csv to a variable called "data", with the address of where the data is located, to know if it was
//loaded correctly we print the schema of the data frame and to show us the first line.
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/salmi/OneDrive/Documentos/GitHub/datos_masivos/Practices/Practice 1/advertising.csv")

data.printSchema()
//root
 //|-- Daily Time Spent on Site: double (nullable = true)
 //|-- Age: integer (nullable = true)
 //|-- Area Income: double (nullable = true)
 //|-- Daily Internet Usage: double (nullable = true)
 //|-- Ad Topic Line: string (nullable = true)
 //|-- City: string (nullable = true)
 //|-- Male: integer (nullable = true)
 //|-- Country: string (nullable = true)
 //|-- Timestamp: timestamp (nullable = true)
 //|-- Clicked on Ad: integer (nullable = true)


///////////////////////
/// Data Display /////
/////////////////////

// Print a single row.

data.head(1)
// Array[org.apache.spark.sql.Row] = Array([68.95,35,61833.9,256.09,Cloned 
//5thgeneration orchestration,Wrightburgh,0,Tunisia,2016-03-27 00:53:11.0,0])

//Analysis:Here the data of the data frame is transformed so that it takes the form of 
//("label", "characteristics"), where we first specify that the columns of the 
//data frame will be and that it is only the first row of data, we add a line break , 
//we specify which is the label, we create our "for" loop where it will only be a range 
//to show the first row, with a length of the number of columns of our data frame, within 
//this for it will print the columns and their respective value of the first row.

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

////////////////////////////////////////////////////
//// Prepare the DataFrame for Machine Learning ////
//////////////////////////////////////////////////

//   Do the following:
//    - Rename the column "Clicked on Ad" to "label"
//    - Take the following columns as features, "Daily Time Spent on Site","Age","Area Income","Daily Internet Usage","Timestamp","Male"
//    - Create a new columns called "Hour" with a timestamp containing  "Hour of the click"

//Analysis:With the variable "timedata" we add a new column called "time" taking the data from the 
//column "Timestamp" that is already existing in the dataframe. After that we select the column 
//"Clicked on Ad" which will now be called "label", we will also take the columns that have 
//numerical data, which will be called "logregdata".

val timedata = data.withColumn("Hour",hour(data("Timestamp")))

val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male")

// Imports the VectorAssembler and Vectors

// Create a new vector VectorAssembler called assembler for the features.

//Analysis:We import the library of "VectorAssembler" and "Vectors", then we create 
//"assembler" where we are going to convert the input values ​​of the data frame, 
//where with the objective of "VectorAssembler" we convert them into a single column 
//which is going to be called "features ”When leaving.

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features"))

// Use randomSplit to create data for train and test divided into 70/30

//Analysis:Here we have two types of data, some will be training and others will be test, 
//which will be 70% and 30% respectively of our data frame, when using "randomSplit"
//to create train and test data divided into 70/30 and with " seed ”is for you to generate
//the random numbers.
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)


///////////////////////////////
// Configure a Pipeline ///////
/////////////////////////////

// Import a  Pipeline
// Create a new LogisticRegression object called lr
// Create a new pipeline object with the following elements: assembler, lr
// Adjust (fit) the pipeline for the training set.


// Take the results from the Test set with transform.

//Analysis:We import the “pipeline” library, we create a logistic regression object, 
//then we create a “pipeline” which will have the elements of “assembler” and 
//“lr” that we created previously, then we create the model which will have the 
//training data and "Results" that will take the results of the model to the test data.

import org.apache.spark.ml.Pipeline

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(assembler, lr))

val model = pipeline.fit(training)

val results = model.transform(test)

////////////////////////////////////
//// Evaluating the model /////////////
//////////////////////////////////

// Import MulticlassMetrics for metrics and evaluation.
// Convert the test results (test) on RDD using .as and .rdd
// Inicialize a MulticlassMetrics object  
// Print the Confusion matrix

//Analysis:We import the library of "VectorAssembler" and "Vectors", then we create "assembler" 
//where we are going to convert the input values ​​of the data frame, where with the objective of 
//"VectorAssembler" we convert them into a single column which is going to be called "features ”
//When leaving.

import org.apache.spark.mllib.evaluation.MulticlassMetrics

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

println("Confusion matrix:")
println(metrics.confusionMatrix)
//scala> println(metrics.confusionMatrix)
//146.0  7.0    
//1.0    161.0

metrics.accuracy
//res9: Double = 0.9746031746031746