# Practice 7

## Multilayer perceptron classifier



```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```


```scala
import org.apache.spark.sql.SparkSession
```


```scala
val data = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")
```

```scala
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)
```

```scala
val layers = Array[Int](4, 5, 4, 3)
```

```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

```scala
val model = trainer.fit(train)
```

```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```


```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```







