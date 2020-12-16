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
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("WholesaleCustomerData.csv")

//Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data
val feature_data = dataframe.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")
feature_data.show(5)
//+-----+----+-------+------+----------------+----------+
//|Fresh|Milk|Grocery|Frozen|Detergents_Paper|Delicassen|
//+-----+----+-------+------+----------------+----------+
//|12669|9656|   7561|   214|            2674|      1338|
//| 7057|9810|   9568|  1762|            3293|      1776|
//| 6353|8808|   7684|  2405|            3516|      7844|
//|13265|1196|   4221|  6404|             507|      1788|
//|22615|5410|   7198|  3915|            1777|      5185|
//+-----+----+-------+------+----------------+----------+

//Import Vector Assembler and Vector
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

//Creates a new Vector Assembler object for the feature columns as a input set, remembering there are no labels
val assembler = (new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features"))

//Use the assembler object to transform feature_data
val output = assembler.transform(feature_data)
output.show(5)
//+-----+----+-------+------+----------------+----------+--------------------+
//|Fresh|Milk|Grocery|Frozen|Detergents_Paper|Delicassen|            features|
//+-----+----+-------+------+----------------+----------+--------------------+
//|12669|9656|   7561|   214|            2674|      1338|[12669.0,9656.0,7...|
//| 7057|9810|   9568|  1762|            3293|      1776|[7057.0,9810.0,95...|
//| 6353|8808|   7684|  2405|            3516|      7844|[6353.0,8808.0,76...|
//|13265|1196|   4221|  6404|             507|      1788|[13265.0,1196.0,4...|
//|22615|5410|   7198|  3915|            1777|      5185|[22615.0,5410.0,7...|
//+-----+----+-------+------+----------------+----------+--------------------+


//Create a Kmeans model with K = 3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(output)

//Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids
val WSSE = model.computeCost(output)
println(s"Within set sum of Squared Errors = $WSSE")
//Within set sum of Squared Errors = 8.095172370767671E10

//print the centroids
println("Cluster Centers: ")
model.clusterCenters.foreach(println)

//Cluster Centers: 
//[7993.574780058651,4196.803519061584,5837.4926686217,2546.624633431085,2016.2873900293255,1151.4193548387098]
//[9928.18918918919,21513.081081081084,30993.486486486487,2960.4324324324325,13996.594594594595,3772.3243243243246]
//[35273.854838709674,5213.919354838709,5826.096774193548,6027.6612903225805,1006.9193548387096,2237.6290322580644]
