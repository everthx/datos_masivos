// Final Proyect

//Made by
//Hernández Negrete Salma Fabel - 16212354
//Luna Fuentes Fernando - 16210975

//Objective: Comparison of the performance following machine learning algorithms
// - SVM
// - Decision Three
// - Logistic regression
// - Multilayer Perceptron


//>>>>>>>>>>>>>>>>>>>SVM<<<<<<<<<<<<<<<<<<<<<<<<<<<

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

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df)

val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

//Fit the model
val lsvcModel = lsvc.fit(output)

// Imprimimos El Coeficiente De Intercepcion
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
//Coefficients: [-2.125897501491213E-6,-0.013517727458849872,7.514021888017163E-4,2.7022337506408964E-4,0.011177544540215354] 
//Intercept: -1.084924165339881

//>>>>>>>>>>>>>>>>>Decision Three<<<<<<<<<<<<<<<<<<




//>>>>>>>>>>>>>>>>>Logistic regression<<<<<<<<<<<<<




//>>>>>>>>>>>>>>>>>Multilayer Perceptron<<<<<<<<<<<<