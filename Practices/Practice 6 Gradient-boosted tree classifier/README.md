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


```scala
import org.apache.spark.sql.SparkSession
```


```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```


```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```





