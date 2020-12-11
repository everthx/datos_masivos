//Practice 9 One-vs-Rest classifier

import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

import org.apache.spark.sql.SparkSession

val inputData = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")

// generate the train/test split.
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

// instantiate the base classifier
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

// instantiate the One Vs Rest Classifier.
val ovr = new OneVsRest().setClassifier(classifier)

// train the multiclass model.
val ovrModel = ovr.fit(train)

// score the model on test data.
val predictions = ovrModel.transform(test)

// obtain evaluator.
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// compute the classification error on test data.
val accuracy = evaluator.evaluate(predictions)//accuracy: Double = 0.8709677419354839
println(s"Test Error = ${1 - accuracy}")//Test Error = 0.12903225806451613

