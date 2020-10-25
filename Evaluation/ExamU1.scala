//Examen 1
//Comienza una simple sesión Spark.
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Cargue el archivo Netflix Stock CSV, haga que Spark infiera los tipos de datos.
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix.csv")
df.show()

//¿Cuáles son los nombres de las columnas?

df.columns//res2: Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close)

//¿Cómo es el esquema?
df.printSchema ()
//Imprime las primeras 5 columnas.
df.head(5) //Imprime filas
df.select($"Date",$"Open",$"High",$"Low",$"Close").show() //Imprime Columnas

//Usa describe () para aprender sobre el DataFrame.
df.describe().show()

//Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la relación entre el precio de la columna “High” frente a la columna “Volume” de
//acciones negociadas por un día. (Hint: Es una operación de columnas).
val dfnew = df.withColumn("HV Ratio", df("High")/df("Volume")).show()

//¿Qué día tuvo el pico más alto en la columna “Close”?
df.orderBy($"Close".desc).show(1)

//Escribe con tus propias palabras en un comentario de tu código. ¿Cuál es el
//significado de la columna Cerrar “Close”?

/*Close is the average relation of exchange between High and Low that Netflix close don that day*/

//¿Cuál es el máximo y mínimo de la columna “Volume”?
df.select(max("Volume")).show()
df.select(min("Volume")).show()

//Con Sintaxis Scala/Spark $ conteste los siguiente:

//Hint: Básicamente muy parecido a la session de dates, tendrán que crear otro
//dataframe para contestar algunos de los incisos.

//¿Cuántos días fue la columna “Close” inferior a $ 600?
df.filter($"Close"<600).count()

//¿Qué porcentaje del tiempo fue la columna “High” mayor que $ 500?
var hdf = (df.filter($"High">500).count()*1.0/df.count())*100

//¿Cuál es la correlación de Pearson entre columna “High” y la columna “Volumen”?
df.stat.corr("High","Volume")

//¿Cuál es el máximo de la columna “High” por año?
df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()

//¿Cuál es el promedio de columna “Close” para cada mes del calendario?
df.groupBy(month(df("Date")).alias("Months")).max("Close").sort(asc("Months")).show()

