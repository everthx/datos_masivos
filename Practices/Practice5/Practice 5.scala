//Practice 5
//Practice with 15 Spark Default Functions for Dataframes, with the link referring to this topic

 import spark.implicits._

  val simpleData = Seq(
    ("James","Sales","NY",90000,34,10000),
    ("Michael","Sales","NY",86000,56,20000),
    ("Robert","Sales","CA",81000,30,23000),
    ("Maria","Finance","CA",90000,24,23000),
    ("Raman","Finance","CA",99000,40,24000),
    ("Scott","Finance","NY",83000,36,19000),
    ("Jen","Finance","NY",79000,53,15000),
    ("Jeff","Marketing","CA",80000,25,18000),
    ("Kumar","Marketing","NY",91000,50,21000)
  )
   val df = simpleData.toDF("employee_name","department","state","salary","age","bonus")
  df.show()
//No.1

df.orderBy("department","state").show(false)

//No.2

df.sort(col("department").asc,col("state").desc).show(false)

//No.3

