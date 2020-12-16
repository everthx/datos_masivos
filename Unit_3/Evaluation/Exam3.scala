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




