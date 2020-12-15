# Practice 4
## Decision Tree Classifier

> 1- Import the libraries needed for the example
```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

import org.apache.spark.sql.SparkSession
```

//val spark = SparkSession.builder().getOrCreate()
> 2- Create a new variable to store the spark session we will be using, lets go ahead and give it a name while we're at it.
```scala
object DecisionTree {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("dtree")
      .getOrCreate()
```
> 3- We will need to load data from somewhere, for this examle we will use the sample_libsvm_data included with scala, lets load it on to a variable we will name data using the spark reading functions
```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```

> 4- We will create a labelIndexer and a featureIndexer variable and fitting that to our current data variable.
```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```

> 5-Now we need a set of data to be our training input and another so we can test if it works. We could assign these values on to two separate variables or (even better) we can use a two dimensional array and have Scala divide it with a built-in function, all we need is to tell it how much percentaje to split, for this example we will go assign 70% of our data to our Training model and 30% to our test source:
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

> 6- Here we create an instance of the Decision Tree Classifier and set 2 specific columns to be our labelIndexer and indexedFeatures. Then we create an instance of IndexToString and set 2 label names that will store our predictions.
```scala
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

```

> 7- We will now create a Pipeline with all the gathered information so far. The Pipeline will allow us to input multiple extimations to a single stages array we can use further down.
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
```

> 8- Now, lets use that Pipeline with all of our information to train our model. We will store the result on a new variable called model:
```scala
val model = pipeline.fit(trainingData)
```

> 9- So, we have trained our model with our training data, it is now time to make some predictions using out test data, we can achieve this with the following line:
```scala
val predictions = model.transform(testData)
```

// Select example rows to display.
> 10- Lets take a look at what the model predicted by selecting some of the coluns and a few rows to display:
```scala
predictions.select("predictedLabel", "label", "features").show(5)
```

> 11- Lets take a look on how precise was that prediction. We will need to call the MulticlassClassificationEvaluator and then call the accuracy based on the predictions. Finally, we will display the Error percentage:
```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```

> 12- The next lines will allow us to create a stage model instance for DecisionTreeClassificationModel and print out the Learned classification of the tree model:
```scala
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
```

//TODO("Print the output")