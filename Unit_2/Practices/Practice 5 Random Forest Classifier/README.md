# Practice 5 
## Random Forest Clasifier

> 1- First we need to import all needed libraries as follows:
```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.attribute.Attribute
```

> 2- Next we need to create a variable to store the data sample inclused with spark
```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```

> 3- So we can always be aware of what our data is we can add labels our data's indexers.
```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
val indexed =  labelIndexer.transform(data)
```

> 4- Once our data is labeled, we will need to tell it which ones will be our features and how many distict values it has. We can do that the following way:
```scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```


> 5- Now we need a set of data to be our training input and another so we can test if it works. We could assign these values on to two separate variables or (even better) we can use a two dimensional array and have Scala divide it with a built-in function, all we need is to tell it how much percentaje to split, for this example we will go assign 70% of our data to our Training model and 30% to our test source:
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

> 6- In this step we will train the model passing the indexed labels, the feaatures we have and the number of trees that we want from it. Then, We create an instance of IndexToString and set 2 label names that will store our predictions.
```scala
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)

val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels) 
```

> 7- We will now create a Pipeline with all the gathered information so far. The Pipeline will allow us to input multiple extimations to a single stages array we can use further down.
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
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

> 12- The next lines will allow us to create a stage model instance for RandomForestClassificationModel and print out the Learned classification of the forest model:
```scala
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]

println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
```

//TODO("Print the output")