# Practice 9

## One-vs-Rest classifier

>OneVsRest is an example of a machine learning reduction for performing multiclass classification given a base classifier that can perform binary classification efficiently. It is also known as “One-vs-All.”

>OneVsRest is implemented as an Estimator. For the base classifier, it takes instances of Classifier and creates a binary classification problem for each of the k classes. The classifier for class i is trained to predict whether the label is i or not, distinguishing class i from all other classes.

>Predictions are done by evaluating each binary classifier and the index of the most confident classifier is output as label.

We import the libraries and log into spark.

```scala
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

import org.apache.spark.sql.SparkSession

```
We load the data to be our data frame.

```scala
val inputData = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")
```

We generate the division of the training and test data.

```scala
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))
```

Instance the base classifier

```scala
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)
```

Create the instance of the One Vs Rest classifier.

```scala
val ovr = new OneVsRest().setClassifier(classifier)
```

Train the multiclass model.

```scala

val ovrModel = ovr.fit(train)
```

We have the model of the test data, we will use the trained model to generate predictions for the test data.

```scala

val predictions = ovrModel.transform(test)
```

We create “evaluator” to pass the predictions to the multi-class classification evaluator to generate a precision value.

```scala

val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

Calculate the sort error in the test data and then print.

```scala

val accuracy = evaluator.evaluate(predictions)//accuracy: Double = 0.8709677419354839
println(s"Test Error = ${1 - accuracy}")//Test Error = 0.12903225806451613

```
