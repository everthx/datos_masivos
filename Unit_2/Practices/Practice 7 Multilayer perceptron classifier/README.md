# Practice 7

## Multilayer perceptron classifier

Import libraries

```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

Login to spark

```scala
import org.apache.spark.sql.SparkSession
```

We load the data to be our data frame

```scala
val data = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")
```

We have the test and training data

```scala
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)
```

We have "layers" of size 4 (characteristics), two intermediate ones of size 5 and 4 and output of size 3 (classes).

```scala
val layers = Array[Int](4, 5, 4, 3)
```

Tenemos “trainer” que crea el entrenador y establece sus parámetros.

```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

"Trainer" model.

```scala
val model = trainer.fit(train)
```

Calculate the precision on the test equipment.

```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

Print the results

```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

//Test set accuracy = 0.9019607843137255
```







