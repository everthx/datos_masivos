# Practice 2

## Linear Regression

### Import linear regression

```scala
import org.apache.spark.ml.regression.LinearRegression
```

### Optional: Use the following code to configure errors.

```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

### Start a simple Spark Session.

```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

```

###  Use Spark for Clean-Ecommerce csv file.

```scala
val data = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/salmi/OneDrive/Documentos/GitHub/datos_masivos/Practices/Practice 2/Clean-Ecommerce.csv")
```

###  Print the schema in the DataFrame.

```scala

data.printSchema()

scala> data.printSchema()
root
 |-- Email: string (nullable = true)
 |-- Avatar: string (nullable = true)
 |-- Avg Session Length: double (nullable = true)
 |-- Time on App: double (nullable = true)
 |-- Time on Website: double (nullable = true)
 |-- Length of Membership: double (nullable = true)
 |-- Yearly Amount Spent: double (nullable = true)

```

### Print a sample line of the Data Frame.

```scala
data.head(1)
```

### Configure the Data Frame for Machine Learning

#### Transform the data frame to take the form of ("label", "characteristics")

```scala
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example df row")
for(ind <- Range(0, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
```
#### Import VectorAssembler and Vectors

```scala

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

```

#### Rename the column Yearly Amount Spent as "label", also from the data take only the numeric column, leave all this as a new Data Frame called df.

```scala
val df = data.select(data("Yearly Amount Spent").as("label"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent")
```

#### Let the assembler object convert the input values ​​to a vector. Use the VectorAssembler object to convert the input columns of the df to a single output column of an array called "features", Set the input columns from where we are supposed to read the values. Call this new assambler.

```scala
val new_assembler = (new VectorAssembler()
                  .setInputCols(Array("Avg Session Length", "Time on App","Time on Website","Length of Membership","Yearly Amount Spent"))
                  .setOutputCol("features"))
```

#### Use the assembler to transform our DataFrame to two columns: label and characteristics.

```scala
val output = new_assembler.transform(df).select($"label", $"features")
output.show()
```

#### Create an object for linear regression model.

```scala
val lr = new LinearRegression()

```

#### Fit the model for the data and call this model lrModelo.

```scala
val lrModel = lr.fit(output)

```

#### Print the coefficients and intercept for linear regression

```scala
println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

```

#### Summarize the model on the training set and print the output of some metrics. Use our model's .summary method to create an object, called trainingSummary

```scala
val trainingSummary = lrModel.summary
```

#### Displays the residual values, the RMSE, the MSE, and also the R ^ 2

```scala
 trainingSummary.residuals.show()
 println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
 println(s"MSE: ${trainingSummary.predictions}")
 println(s"r2: ${trainingSummary.r2}")
```





