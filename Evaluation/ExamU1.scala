//Examen 1
//Comienza una simple sesión Spark.
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
//Cargue el archivo Netflix Stock CSV, haga que Spark infiera los tipos de datos.
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

//¿Cuáles son los nombres de las columnas?

df.columns//res2: Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close)

//¿Cómo es el esquema?

//Imprime las primeras 5 columnas.
df.head(5)
//Usa describe () para aprender sobre el DataFrame.
df.describe().show()
//Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la relación entre el precio de la columna “High” frente a la columna “Volume” de
//acciones negociadas por un día. (Hint: Es una operación de columnas).
val dfnew = df.withColumn("HV Ratio", df("High")+df("Volume"))

dfnew.show()

//¿Qué día tuvo el pico más alto en la columna “Close”?


//Escribe con tus propias palabras en un comentario de tu código. ¿Cuál es el
//significado de la columna Cerrar “Close”?
