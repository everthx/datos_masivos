# Practice 6

## Gradient-boosted tree classifier

Gradient-powered trees (GBT) are a popular classification and regression method that uses sets of decision trees. More information about the implementation can be found in spark.ml in the section on GBT.

We import the libraries

```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```

We log in to spark

```scala
import org.apache.spark.sql.SparkSession
```

We load the data to be our data frame

```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```

So that we can always be aware of the searches in our data, we can add tags to the data indexers.

```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```
Once our data is labeled, we will have to tell you what our characteristics will be and how many distinctive values ​​it has.

```scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```

Now we need one dataset to be our training input and another so we can test if it works. We could assign these values ​​to two separate variables or (even better) we can use a two-dimensional array and have Scala divide it with a built-in function, all we need is to tell you how much percentage to divide, for this example we will assign 70% of our data to our training model and 30% to our test source

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

GBT model

```scala
val gbt = new GBTClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setMaxIter(10).setFeatureSubsetStrategy("auto")
```

The indexed tag becomes the original.

```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```

We create a “pipeline” that joins the GBT model with the labels.

```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
```

Pipeline training data

```scala
val model = pipeline.fit(trainingData)
```

Prediction of our "model" data

```scala
val predictions = model.transform(testData)
```

We select our prediction data and it shows 5.

```scala
predictions.select("predictedLabel", "label", "features").show(5)
```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Gradient-boosted%20tree%20classifier%201.PNG)


We select the predictions, the label and the accuracy to calculate the "test error".

```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
//accuracy: Double = 0.967741935483871

println(s"Test Error = ${1.0 - accuracy}")
//Test Error = 0.032258064516129004

```
print gbt tree

```scala
val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")
```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Gradient-boosted%20tree%20classifier%202.PNG)




