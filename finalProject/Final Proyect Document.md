<html>
<h2 align="center" > Final Project Document</h2>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/portada.PNG)

</div>

<h3 align="center" >Instituto Nacional de México</h3>
<h3 align="center" >Instituto Tecnológico de Tijuana</h3>
<h3 align="center" >Departamento de sistemas y computación</h3>
<br>
<h4 align="center" >Ingeniería Informática</h4>
<h4 align="center" >Ingeniería en Sistemas Computacionales</h4>
<h4 align="center" >Semester of Sep 2020 - Ene 2021</h4>
<br>
<h4 align="center" >Datos Masivos | BDD-1704TI9A</h4>
<br>
<h4 align="center" >Unit 4</h4>
<h4 align="center" >Final Project</h4>
<br>
<h4 align="center" >Made by : </h4>
<h4 align="center" >Hernandez Negrete Salma Fabel - 16212354</h4>
<h4 align="center" >Luna Fuentes Fernando - 16210975</h4>
<br>
<h4 align="center" >Professor : </h4>
<h4 align="center" >Dr. Jose Christian Romero Hernandez</h4>
<br>
<h4 align="center" >Delivery date: January 2021</h4>
</html>

# Index
- [Introduction](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#introduction)
- [Theoretical framework of algorithms](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#theoretical-framework-of-algorithms)
- [Implementation](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#implementation)
  - [SVM](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#svm---support-vector-machines-1)
  - [Decision Tree](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#decision-three-1)
  - [Logistic regression](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#logistic-regression-1)
  - [Multilayer Perceptron](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#multilayer-perceptron-1)
- [Results](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#results)
- [Conclusions](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#conclusions)
- [References](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#references)

# Introduction

<p align="justify" >
This document specifies the analysis and development of the final project for unit 4 on the matter of big data, which the objective of this final project is to compare the performance of Machine Learning algorithms that were seenstudied during the semester, using  the Bank Marketing data set obtained from the following source:<br>
https://archive.ics.uci.edu/ml/datasets/Bank+Marketing <br>
The implementation of these algorithms is explained, as well as the result that was reached, through a tabulation to better visualize the performance of each one of them.
</p>

# Algorithms Theoretical Framework.

### SVM - Support Vector Machines

<p align="justify" >
The acronym SVM stands for Support Vector Machines. They can be used for both regression and classification. Support vector machines are a machine learning technique that finds the best possible separation between classes. With two dimensions it is easy to understand what you are doing. Typically, machine learning problems have many dimensions. So instead of finding the optimal line, the SVM finds the hyperplane that maximizes the separation margin between classes. [1]
</p>

<p align="justify" >
The support vectors are the points that define the maximum margin of separation of the hyperplane that separates the classes. They are called vectors, instead of points, because these "points" have as many elements as the dimensions of our input space. That is, these multi-dimensional points are represented with an n-dimensional vector. [1]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/IMAGEN%20Linear%20Support%20Vector%20Machine.PNG)

</div>

<p align="justify" >
There are two types of:

- **Linear SVM:** Linear SVM is used for linearly separable data, which means that if a data set can be classified into two classes using a single straight line, then such data is called linearly separable data and the classifier is used as linear SVM classifier. .
- **Non-linear SVM:** A non-linear SVM is used for non-linear separated data, which means that if a data set cannot be classified by a straight line, then the data is called non-linear data and the classifier used is called non-linear linear SVM classifier. [2]
</p>

### Decision Tree

<p align="justify" >
In decision analysis, you can use a decision tree to visually and explicitly represent decisions and decision making. As its name implies, it uses a decision model in the form of a tree. Although it is a commonly used tool in data mining to derive a strategy to achieve a particular goal, it is also widely used in machine learning. [3]
</p>
<p align="justify" >
It is a tree-structured classifier, where the internal nodes represent the characteristics of a data set, the branches represent the decision rules, and each leaf node represents the result. [4]
</p>
<p align="justify" >
In a decision tree, there are two nodes, which are the decision node and the leaf node. Decision nodes are used to make any decision and have multiple branches, while leaf nodes are the result of those decisions and contain no more branches. Decisions or tests are made based on the characteristics of the given data set. [4]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/three.PNG)

</div>

<p align="justify" >
A decision tree is drawn upside down with its root at the top. In the image to the left, the bold black text represents an internal node / condition, according to which the tree is divided into branches / edges. The end of the branch that is no longer divided is the decision / leaf, in this case, whether the passenger died or survived, represented as red and green text respectively. [3]
</p>

### Logistic regression

<p align="justify" >
Logistic regression is a predictive modeling algorithm that is used when the variable Y is binarily categorical. That is, it can only take two values ​​such as 1 or 0. The goal is to determine a mathematical equation that can be used to predict the probability of event 1. Once the equation is established, it can be used to predict the Y when only X is known. [5]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/logistic.PNG)

</div>

<p align="justify" >
Logistic regression is used when the dependent variable (target) is categorical.
</p>

For example:

- To predict whether an email is spam (1) or (0)
- Whether the tumor is malignant (1) or not (0) 

<p align="justify" >
Let's consider a scenario in which we need to classify whether an email is spam or not. If we use linear regression for this problem, it is necessary to establish a threshold based on which classification can be performed. Let's say if the actual class is malignant, the predicted continuous value of 0.4, and the threshold value is 0.5, the data point will be classified as non-malignant, which can have serious consequences in real time.
</p>

<p align="justify" >
From this example, it can be inferred that linear regression is not suitable for classification problems. Linear regression has no limits and this brings logistic regression to the picture. Its value goes strictly from 0 to 1. [6]
</p>

### Multilayer Perceptron

<p align="justify" >
The Multilayer Perceptron (MLP) is a complement to the forward neural network. It consists of Tree types of layers: the input layer, the output layer, and the hidden layer, as shown in the figure. The input layer receives the input signal to be processed. The required task, such as prediction and classification, is performed by the output layer. An arbitrary number of hidden layers that are placed between the input and output layers are the true computational engine of MLP. Similar to a feed-forward network in an MLP, data flows in the forward direction from the input layer to the output layer. Neurons in the MLP are trained with the backpropagation learning algorithm. MLPs are designed to approximate any continuous function and can solve problems that are not linearly separable. The main use cases for MLP are pattern classification, recognition, prediction, and approximation. [7]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/multilpe.PNG)

</div>

<p align="justify" >
Multilayer perceptrons are often applied to supervised learning problems: they train on a set of input-output pairs and learn to model the correlation (or dependencies) between those inputs and outputs. Training involves adjusting the parameters, or the weights and biases, of the model to minimize error. [8]
</p>

# Implementation

### Tool to be used

<p align="justify" >
For the implementation of the algorithms presented above, the Apache Spark framework will be used, which is a computing system that is based on Hadoop Map Reduce and that, mainly, allows us to divide or parallelize the work, since it is usually installed in a machine cluster.
</p>

<p align="justify" >
It also allows working on disk. In this way, if, for example, we have a very large file or a large quantity of information that does not fit into memory, the tool allows us to store part of it on disk, which slows down. This means that we have to try to find the balance between what is stored in memory and what is stored on disk, to have a good speed and so that the cost is not too high, since memory is always much more expensive than disk memory. Furthermore, Spark is multilingual so it can be programmed in Scala, Python, Java or R. [9]
</p>

<p align="justify" >
So we implemented Spark with Scala, which is a modern multi-paradigm programming language designed to express common programming patterns in a concise, elegant, and securely typed way. It easily integrates features of functional and object-oriented languages. [10]
</p>

### Tool selection justification.

<p align="justify" >
The reason why Spark is chosen with Scala is because it is a programming language with a journey since it has been in the market for two decades. In addition, Scalable language (Scala), is a hybrid language between object-oriented programming and functional programming. Therefore, having the advantages of one and the other, it is a quite functional and practical language. [11]
</p>

<p align="justify" >
It has less code to perform some functions compared to other languages. This is useful because you can reduce the code to the minimum expression and thus read it faster to correct possible problems [11]. Since this final project's goal is to compare the performance of several Machine Learning algorithms, this also offers access to modules and libraries that cover Machine Learning.
</p>


### Preparation of our Dataframe

<p align="justify" >
To begin, you need to know our dataset with which we are going to work called “bank-full.csv”, which contains data related to direct marketing campaigns of a Portuguese banking institution. The marketing campaigns were based on phone calls.
</p>

To start we need to accommodate our data frame and for this the following libraries were used.

```scala
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

The following lines of code were used to minimize errors.

```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

We start a simple session on spark and its instance.

``` scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
```

<p align="justify" >
Then the dataset "bank-full.csv" was loaded, which is our data frame, if you have a header with column names in the file, you must explicitly specify the "true" header option "option (" header ", true ) ”Not to mention this, the API treats the header as a data record. Next we have“ inferschema ”from the header record and derive the column type based on the data. Then we tell it that our data is delimited with“; the csv data.
</p>

``` scala
val dataframe = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")
```
Once it was loaded, we showed the schematic of the dataframe.

``` scala
dataframe.printSchema()
//|-- age: integer (nullable = true)
//|-- job: string (nullable = true)
//|-- marital: string (nullable = true)
//|-- education: string (nullable = true)
//|-- default: string (nullable = true)
//|-- balance: integer (nullable = true)
//|-- housing: string (nullable = true)
//|-- loan: string (nullable = true)
//|-- contact: string (nullable = true)
//|-- day: integer (nullable = true)
//|-- month: string (nullable = true)
//|-- duration: integer (nullable = true)
//|-- campaign: integer (nullable = true)
//|-- pdays: integer (nullable = true)
//|-- previous: integer (nullable = true)
//|-- poutcome: string (nullable = true)
//|-- y: string (nullable = true)
```

<p align="justify" >
Once we understand how our dataframe is made up, it is known that the column of "y" is an output variable and is binary of "yes" or "no" so it becomes our label.
</p>

<p align="justify" >
We use StringIndexer () which encodes a column of tag strings into a column of tag indexes. By default, this is sorted by tag frequencies, so the most frequent tag gets index 0.
</p>

``` scala
val stringindexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val df = stringindexer.fit(dataframe).transform(dataframe)
```
<p align="justify" >
The assembler object converts the input values ​​to a vector. VectorAssembler is used to convert the input columns of the df to a single output column of an array called "features". The columns that are integer "balance", "day", "duration", "campaign", "pdays", "previous" are taken, the old one was discarded since it is not necessary.
</p>

```scala
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val output = assembler.transform(df).select($"label", $"features")
output.show(5)
+-----+--------------------+
|label|            features|
+-----+--------------------+
|  0.0|[2143.0,5.0,261.0...|
|  0.0|[29.0,5.0,151.0,1...|
|  0.0|[2.0,5.0,76.0,1.0...|
|  0.0|[1506.0,5.0,92.0,...|
|  0.0|[1.0,5.0,198.0,1....|
+-----+--------------------+
```

## SVM - Support Vector Machines

<p align="justify" >

</p>

``` scala
val Array(training, test) = output.randomSplit(Array(0.7, 0.3), seed = 12345)
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```

```scala
val lsvcModel = lsvc.fit(training)
val results = lsvcModel.transform(test)
```


``` scala
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)
```

``` scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
//Coefficients: [4.339356943245717E-6,-0.004343870375279081,5.765546723075568E-4,-0.07211029685388683,2.5540225773264664E-4,0.007528323053442825] 
//Intercept: -1.07258737561311
```

```scala
println("Accurancy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")
```

## Decision Tree

## Logistic regression

## Multilayer Perceptron

# Results
<p align="justify" >
After comparing the results of each of the algorithms we decided to express that comparison based on Accuracy and Error percentages and give them a rank.
</p>

<div  align="center" >

|      ML Algorithm     | Accuracy % | Test Error % | Rank by Accuracy |
|:---------------------:|:----------:|:------------:|:----------------:|
|          SVM          |   0.8449   |    0.1150    |         4        |
|     Desicion Tree     |   0.8924   |    0.1075    |         1        |
|  Logistic Regression  |   0.8911   |    0.1088    |         2        |
| Multilayer Perceptron |   0.8829   |    0.1170    |         3        |

</div>

# Conclusions


# References

>[1] José Martínez Heras. (28/05/2019). Máquinas de Vectores de Soporte (SVM). 23/12/2020, de iartificial.net Sitio web: https://www.iartificial.net/maquinas-de-vectores-de-soporte-svm/

>[2] JavaTpoint. (2018). Algoritmo de máquina de vectores de soporte. 23/12/2020, de JavaTpoint Sitio web: https://www.javatpoint.com/machine-learning-support-vector-machine-algorithm

>[3] Prashant Gupta. (17/05/2017). Árboles de decisión en el aprendizaje automático. 23/12/2020, de towardsdatascience.com Sitio web: https://towardsdatascience.com/decision-trees-in-machine-learning-641b9c4e8052

>[4] JavaTpoint. (2018). Algoritmo de clasificación de árboles de decisión. 23/12/2020, de JavaTpoint Sitio web: https://www.javatpoint.com/machine-learning-decision-tree-classification-algorithm

>[5] Selva Prabhakaran.Regresión logística. 23/12/2020, de machine learning plus Sitio web: https://www.machinelearningplus.com/machine-learning/logistic-regression-tutorial-examples-r/

>[6] Saishruthi Swaminathan. (15/05/2018). Regresión logística: descripción general detallada. 23/12/2020, de towards data science Sitio web: https://towardsdatascience.com/logistic-regression-detailed-overview-46c4da4303bc

>[7] S. Abirami , P. Chitra. (2020). Multilayer Perceptron. 27/12/2020, de sciencedirect Sitio web: https://www.sciencedirect.com/topics/computer-science/multilayer-perceptron

>[8] Chris Nicholson. Una guía para principiantes sobre perceptrones multicapa (MLP). 27/12/2020, de pathmind Sitio web: https://wiki.pathmind.com/multilayer-perceptron

>[9] Abraham Requena Mesa. (2018). ¿Qué es Apache Spark?. 27/12/2020, de openwebinars Sitio web: https://openwebinars.net/blog/que-es-apache-spark/

>[10] alvarag. TOUR OF SCALA. 27/12/2020, de docs.scala-lang Sitio web: https://docs.scala-lang.org/es/tour/tour-of-scala.html#:~:text=Scala%20es%20un%20lenguaje%20de,orientados%20a%20objetos%20y%20funcionales

>[11] keep coding. ¿Cómo usar Spark con Scala para Big Data?  31/12/2020, de keepcoding Sitio web: https://keepcoding.io/blog/usar-spark-con-scala/ 
