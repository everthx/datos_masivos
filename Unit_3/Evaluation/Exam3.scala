//Exam U3

//Import a simple Spark session
import org.apache.spark.sql.SparkSession

//Use lines of code to minimize errors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Create an instance of the Spark session
val spark = SparkSession.builder().getOrCreate()

//Import the Kmeans library for the clustering algorithm
import org.apache.spark.ml.clustering.KMeans

//Loads the Wholesale Customers Data dataset
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("WholesaleCustomersData.csv")

//Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data

//Import Vector Assembler and Vector
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

//Creates a new Vector Assembler object for the feature columns as a input set, remembering there are no labels

//Use the assembler object to transform feature_data

//Create a Kmeans model with K = 3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit()

//Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids.
val WSSE = model.computeCost()
println(s"Within set sum of Squared Errors = $WSSE")

//print the centroids.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
