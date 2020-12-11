# Practice 10

## Naive Bayes

>Naive Bayes classifiers are a family of simple probabilistic, multiclass classifiers based on applying Bayes’ theorem with strong (naive) independence assumptions between every pair of features.

>Naive Bayes can be trained very efficiently. With a single pass over the training data, it computes the conditional probability distribution of each feature given each label. For prediction, it applies Bayes’ theorem to compute the conditional probability distribution of each label given an observation.

We import the libraries and log into spark.

```scala
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

import org.apache.spark.sql.SparkSession

```
We load the data to be our data frame.

```scala
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")
```

Separate data into training and test sets (30% reserved for testing).

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)
```

Train a Naive Bayes model.

```scala
val model = new NaiveBayes().fit(trainingData)
```

Select the sample rows to display.

```scala
val predictions = model.transform(testData)
predictions.show()
```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/naivebayes1.PNG)

Select (prediction, true label) and calculate test errors.

```scala

val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
   
val accuracy = evaluator.evaluate(predictions)
    
println(s"Test set accuracy = $accuracy")

```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/naivebayes2.PNG)


