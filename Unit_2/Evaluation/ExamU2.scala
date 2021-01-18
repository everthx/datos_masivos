//Exam Unit 2

//Import the required libraries
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Load the DataFrame
val dataframe = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

//Print the squema
dataframe.printSchema()
 //|-- sepal_length: double (nullable = true)
 //|-- sepal_width: double (nullable = true)
 //|-- petal_length: double (nullable = true)
 //|-- petal_width: double (nullable = true)
 //|-- species: string (nullable = true)


//Print the first 5 columns
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


//Transform categorical data to numeric
//StringIndexer encodes a string column of labels to a column of label indices.
val  stringindexer = new StringIndexer().setInputCol("species").setOutputCol("label")
val df =  stringindexer.fit(dataframe).transform(dataframe)

//New Columns
df.columns
// Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species, label)

//Print the new squema
df.printSchema()
 //|-- sepal_length: double (nullable = true)
// |-- sepal_width: double (nullable = true)
 //|-- petal_length: double (nullable = true)
// |-- petal_width: double (nullable = true)
// |-- species: string (nullable = true)
// |-- label: double (nullable = false)

//Print the first 5 columns
df.show(5)
//+------------+-----------+------------+-----------+-------+-----+
//|sepal_length|sepal_width|petal_length|petal_width|species|label|
//+------------+-----------+------------+-----------+-------+-----+
//|         5.1|        3.5|         1.4|        0.2| setosa|  2.0|
//|         4.9|        3.0|         1.4|        0.2| setosa|  2.0|
//|         4.7|        3.2|         1.3|        0.2| setosa|  2.0|
//|         4.6|        3.1|         1.5|        0.2| setosa|  2.0|
//|         5.0|        3.6|         1.4|        0.2| setosa|  2.0|
//+------------+-----------+------------+-----------+-------+-----+

//Use the describe() method to learn about the data on the dataframe
df.describe().show()
//+-------+------------------+-------------------+------------------+------------------+---------+------------------+
//|summary|      sepal_length|        sepal_width|      petal_length|       petal_width|  species|             label|
//+-------+------------------+-------------------+------------------+------------------+---------+------------------+
//|  count|               150|                150|               150|               150|      150|               150|
//|   mean| 5.843333333333335| 3.0540000000000007|3.7586666666666693|1.1986666666666672|     null|               1.0|
//| stddev|0.8280661279778637|0.43359431136217375| 1.764420419952262|0.7631607417008414|     null|0.8192319205190403|
//|    min|               4.3|                2.0|               1.0|               0.1|   setosa|               0.0|
//|    max|               7.9|                4.4|               6.9|               2.5|virginica|               2.0|
//+-------+------------------+-------------------+------------------+------------------+---------+------------------+

//Let the assembler object converts the input values ​​to a vector. Use the VectorAssembler object to convert the input columns of the df to a single output column of an array called "features", set the input columns from where we are supposed to read the values.
val assembler = (new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width","petal_length", "petal_width")).setOutputCol("features"))

val output = assembler.transform(df).select($"label", $"features")

output.show(5)
//+-----+-----------------+
//|label|         features|
//+-----+-----------------+
//|  2.0|[5.1,3.5,1.4,0.2]|
//|  2.0|[4.9,3.0,1.4,0.2]|
//|  2.0|[4.7,3.2,1.3,0.2]|
//|  2.0|[4.6,3.1,1.5,0.2]|
//|  2.0|[5.0,3.6,1.4,0.2]|
//+-----+-----------------+
//only showing top 5 rows   


//MultilayerPerceptronClassifier
//Separates the characteristics into 70% training and 30% testing
val splits = output.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

//We have "layers" with a size of 4 (characteristics), two intermediate ones of size 5 and 4 and output of size 3 (classes).
val layers = Array[Int](4, 5, 4, 3)

//We have a “trainer” that the coach creates and establishes its parameters.
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//Now we create the model with the trainer and training data.
val model = trainer.fit(train)

//Calculate the precision on the test equipment
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
//Test set accuracy = 0.925