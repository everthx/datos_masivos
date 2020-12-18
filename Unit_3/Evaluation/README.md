# Datos Masivos: Unit 3 Evaluation
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**

## Instructions:
> Do the following instructions in Spark with the Scala programming language.

## Objective:
> The objective of this practical exam is to try and group the region specific customers of a wholesale distributor. This based on the sales of some product categories.

> The data source is found in the following repository:
https://github.com/jcromerohdz/BigData/blob/master/Spark_clustering/Wholesale%20customers%20data.csv

## 1. Import a simple Spark session.
```scala
import org.apache.spark.sql.SparkSession
```

## 2. Use the lines of code to minimize errors.
```scala
import apache.log4j._
Logger.getLogger("org").setLevel(level.ERROR)
```

## 3. Create an instance of Spark Session.
```scala
val spark = SparkSession.builder().getOrCreate())
```

## 4. Import the Kmeans library for the clustering algorythm.
```scala
import org.apaeche.spark.ml.clustering.KMeans
```

## 5. Load the WholesaleCustomerData dataset.
```scala
val dataframe = spark.read.option("header", "true").option("inferSchema", "true")csv("WholesaleCustomerData.csv")
```

## 6. Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set "feature_data".
```scala
val feature_data = dataframe.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")
```

## 7. Import Vector Assembler and Vector
```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

## 8. Create a new Vector Assembler object as the input for characteristic columns, remembering that there are no labels.
```scala
val assembler = (new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features"))
```

## 9. Use the assembler object to transform feature_data.
```scala
val output = assembler.transform(feature_data)
```

## 10. Creare a Kmeans model with K=3.
```scala
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(output)
```

## 11. Evaluate the groups using Witin Set Sum of Squared Errors WSSE and print the centroids.
```scala
val WSSE = model.computeCost(output)
println(s"Within Set Sum of Squared Errors = $WSSE")

model.clusterCenters.foreach(println)
```

[Video Explanation](https://youtu.be/Wh0iJEQ3yT0)