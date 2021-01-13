# Practice 8

## Linear Support Vector Machine

A support vector machine constructs a hyperplane or set of hyperplanes in a high- or infinite-dimensional space, which can be used for classification, regression, or other tasks. Intuitively, a good separation is achieved by the hyperplane that has the largest distance to the nearest training-data points of any class (so-called functional margin), since in general the larger the margin the lower the generalization error of the classifier. LinearSVC in Spark ML supports binary classification with linear SVM. Internally, it optimizes the Hinge Loss using OWLQN optimizer.

### Repository

https://github.com/SalmaFabel/Linear-Support-Vector-Machine.git

Importing libraries and login in spark

```scala
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.SparkSession
```

We load the data to be our data frame

```scala
val training = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")
```

We have "lsvc" that sets the number of iterations to 10 with the setMaxIter method and Set the regularization parameter.

```scala
val lsvc = new LinearSVC()
      .setMaxIter(10)
      .setRegParam(0.1)
```

Fit the model

```scala
val lsvcModel = lsvc.fit(training)
```

Print coefficients and intercept for linear service

```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Linear%20Support%20Vector%20Machine1.PNG)

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Linear%20Support%20Vector%20Machine2.PNG)


