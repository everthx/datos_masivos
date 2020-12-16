# Datos Masivos: Unit 3 Evaluation
**By Hernandez Negrete Salma Fabel - 16212354 & Luna Fuentes Fernando 16210975**

## Instrucciones
> Desarrolle las siguientes instrucciones en Spark con el lenguaje de programación Scala.

## Objetivo:
> El objetivo de este examen practico es tratar de agrupar los clientes de regiones específicas de un distribuidor al mayoreo. Esto en base a las ventas de algunas categorías de productos.

> Las fuente de datos se encuentra en el repositorio:
https://github.com/jcromerohdz/BigData/blob/master/Spark_clustering/Wholesale%20customers%20data.csv

## 1. Import a simple Spark session.
```scala

```

## 2. Use the lines of code to minimize errors.
```scala

```

## 3. Create an instance of Spark Session.
```scala

```

## 4. Import the Kmeans library for the clustering algorythm.
```scala

```

## 5. Load the WholesaleCustomerData dataset.
```scala

```

## 6. Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set "feature_data".
```scala

```

## 7. Import Vector Assembler and Vector
```scala

```

## 8. Create a new Vector Assembler object as the input for characteristic columns, remembering that there are no labels.
```scala

```

## 9. Use the assembler object to transform feature_data.
```scala

```

## 10. Creare a Kmeans model with K=3.
```scala

```

## 11. Evaluate the groups using Witin Set Sum of Squared Errors WSSE and print the centroids.
```scala

```

[Video Explanation]()