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

Here the same is done as in the previous one, only it is assigned the value of the spearman correlation.

```scala
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")

```

### Hypothesis testing




### Summarizer


