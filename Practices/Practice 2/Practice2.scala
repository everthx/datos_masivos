//Practice 2

////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
/// Coplete las tareas comentadas ///
/////////////////////////////////////////

// Import LinearRegression
import org.apache.spark.ml.regression.LinearRegression

// Opcional: Utilice el siguiente codigo para configurar errores
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Inicie una simple Sesion Spark
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Utilice Spark para el archivo csv Clean-Ecommerce.
val data = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/salmi/OneDrive/Documentos/GitHub/datos_masivos/Practices/Practice 1/Clean-Ecommerce.csv")

// Imprima el schema en el DataFrame.
data.printSchema()

// Imprima un renglon de ejemplo del DataFrane.
data.head(1)

//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(0, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

// Importe VectorAssembler y Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica 
// Deje todo esto como un nuevo DataFrame que se llame df

val df = data.select(data("Yearly Amount Spent").as("label"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent")

// Que el objeto assembler convierta los valores de entrada a un vector
// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
// a una sola columna de salida de un arreglo llamado  "features"
// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assambler.

val new_assembler = (new VectorAssembler()
                  .setInputCols(Array("Avg Session Length", "Time on App","Time on Website","Length of Membership","Yearly Amount Spent"))
                  .setOutputCol("features"))

// Utilice el assembler para transform nuestro DataFrame a dos columnas: label and features

val output = new_assembler.transform(df).select($"label", $"features")
output.show()

// Crear un objeto para modelo de regresion linea.

 val lr = new LinearRegression()

// Ajuste el modelo para los datos y llame a este modelo lrModelo

val lrModel = lr.fit(output)

// Imprima the  coefficients y intercept para la regresion lineal
 println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

// Resuma el modelo sobre el conjunto de entrenamiento imprima la salida de algunas metricas!
// Utilize metodo .summary de nuestro  modelo para crear un objeto
// llamado trainingSummary

val trainingSummary = lrModel.summary

  // Muestre los valores de residuals, el RMSE, el MSE, y tambien el R^2 .

  trainingSummary.residuals.show()
  println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
  println(s"MSE: ${trainingSummary.predictions}")
  println(s"r2: ${trainingSummary.r2}")

// Buen trabajo!