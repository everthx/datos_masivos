# Practice 3

## Basic Statistics

### Correlation

Computes the correlation matrix for the vector input data set using the specified method. The output will be a DataFrame that contains the vector column correlation matrix.

We import the following libraries.

```scala
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
```

We start a simple Spark Session.

```scala
import org.apache.spark.sql.SparkSession
```

A value called data is created and a sequence of vectors is assigned as a value.

```scala
val data = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )
```

A dataframe is created to which the value of a Tuple called Tuple1 is assigned, the data frame contains a column called feautures.

```scala

val df = data.map(Tuple1.apply).toDF("features")

```

A Row type value called coefficient1 of a matrix is ​​assigned the value of the pearson correlation applied to the data frame, applied to its features column.

```scala
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1")

```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Correlation1.PNG)

Here the same is done as in the previous one, only it is assigned the value of the spearman correlation.

```scala
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")

```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Correlation2.PNG)

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Correlation3.PNG)


### Hypothesis testing

ChiSquareTest performs the Pearson independence test for each characteristic compared to the label. For each characteristic, the pairs (characteristic, label) are converted into a contingency matrix for which the Chi-square statistic is calculated. All tag and feature values ​​must be categorical.

First we must the following libraries "ChiSquareTest".

```scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest
```

We start a simple Spark Session.

```scala
import org.apache.spark.sql.SparkSession
```

We create "data" which will have a sequence of vectors.

```scala
val data = Seq(
      (0.0, Vectors.dense(0.5, 10.0)),
      (0.0, Vectors.dense(1.5, 20.0)),
      (1.0, Vectors.dense(1.5, 30.0)),
      (0.0, Vectors.dense(3.5, 30.0)),
      (0.0, Vectors.dense(3.5, 40.0)),
      (1.0, Vectors.dense(3.5, 40.0))
    )
```

We create "df" which will take the "data" that was created previously, we will add "label" and "features".

```scala
val df = data.toDF("label", "features")

```

We create "chi" which has the library "ChiSquareTest", which has our "df" with "label" and "features".

```scala
val chi = ChiSquareTest.test(df, "features", "label").head
```

We print

```scala
println(s"pValues = ${chi.getAs[Vector](0)}")
println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
println(s"statistics ${chi.getAs[Vector](2)}")
```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Hypothesis%20testing%201.PNG)

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Hypothesis%20testing%202.PNG)

### Summarizer

We provide vector column summary statistics for Dataframe all Summarizer. Available metrics are the maximum, minimum, mean, variance, and number of non-zeros in columns, as well as the total count.

We import the following libraries.

```scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer

```

We start a simple Spark Session.

```scala
import org.apache.spark.sql.SparkSession

```

We import "Summarizer".

```scala
import spark.implicits._
import Summarizer._

```

We create "data" that will have two vectors.

```scala
val data = Seq(
      (Vectors.dense(2.0, 3.0, 5.0), 1.0),
      (Vectors.dense(4.0, 6.0, 7.0), 2.0)
    )

```

We create "df" which will have our "data" with "Feactures" and "weight"

```scala
val df = data.toDF("features", "weight")
//scala> val df = data.toDF("features", "weight")
df: org.apache.spark.sql.DataFrame = [features: vector, weight: double]

```

We select metrics (Mean, Variance), then “summary” which will have “Feactures” and “weight”, we assign it a name that will be “summary”, it will show us the mean and variance.

```scala
val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
      .summary($"features", $"weight").as("summary"))
      .select("summary.mean", "summary.variance")
      .as[(Vector, Vector)].first()

//meanVal: org.apache.spark.ml.linalg.Vector = [3.333333333333333,5.0,6.333333333333333]
varianceVal: org.apache.spark.ml.linalg.Vector = [2.0,4.5,2.0]

```

It will print the mean and variance with "weight"

```scala
println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")
//with weight: mean = [3.333333333333333,5.0,6.333333333333333], variance = [2.0,4.5,2.0]

```

It will print the mean of "features" and its variance


```scala
val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
      .as[(Vector, Vector)].first()
//meanVal2: org.apache.spark.ml.linalg.Vector = [3.0,4.5,6.0]
varianceVal2: org.apache.spark.ml.linalg.Vector = [2.0,4.5,2.0]

```

It will print the mean and the sum without "weight"

```scala
println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")
//without weight: mean = [3.0,4.5,6.0], sum = [2.0,4.5,2.0]

```
