<html>
<h2 align="center" > Final Project Document</h2>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/portada.PNG)

</div>

<h3 align="center" >Instituto Nacional de México</h3>
<h3 align="center" >Instituto Tecnológico de Tijuana</h3>
<h3 align="center" >Departamento de sistemas y computación</h3>
<br>
<h4 align="center" >Ingeniería informática</h4>
<h4 align="center" >Semestre Sep - Ene 2020</h4>
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
<h4 align="center" >Teacher : </h4>
<h4 align="center" >Dr. Jose Christian Romero Hernandez</h4>
<br>
<h4 align="center" >Delivery date: January 2021</h4>
</html>

# Index
- [Introduction](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#introduction)
- [Theoretical framework of algorithms](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#theoretical-framework-of-algorithms)
- [Implementation](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#implementation)
  - [SVM]()
  - [Decision Three]()
  - [Logistic regression]()
  - [Multilayer Perceptron]()
- [Results](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#results)
- [Conclusions](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#conclusions)
- [References](https://github.com/everthx/datos_masivos/blob/finalProject/finalProject/Final%20Proyect%20Document.md#references)

# Introduction

<p align="justify" >
This document specifies the analysis and development of the final project of unit 4 of the matter of big data, which the objective of this final project is to compare the Machine Learning performance algorithms that were seen during the semester, which The Bank Marketing data set obtained from the following source will be used:
https://archive.ics.uci.edu/ml/datasets/Bank+Marketing
The implementation of these algorithms is explained, as well as the result that was reached, through a tabulation to better visualize the performance of each one of them.
</p>

# Theoretical framework of algorithms

### SVM - Support Vector Machines

<p align="justify" >
The acronym SVM for its acronym in English (Support Vector Machines). They can be used for both regression and classification. Support vector machines are a machine learning technique that finds the best possible separation between classes. With two dimensions it is easy to understand what you are doing. Typically, machine learning problems have many dimensions. So instead of finding the optimal line, the SVM finds the hyperplane that maximizes the separation margin between classes. [1]
</p>

<p align="justify" >
The support vectors are the points that define the maximum margin of separation of the hyperplane that separates the classes. They are called vectors, instead of points, because these "points" have as many elements as the dimensions of our input space. That is, these multi-dimensional points are represented with an n-dimensional vector. [1]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/IMAGEN%20Linear%20Support%20Vector%20Machine.PNG)

</div>

<p align="justify" >
SVM can be of two types:

- **Linear SVM:** Linear SVM is used for linearly separable data, which means that if a data set can be classified into two classes using a single straight line, then such data is called linearly separable data and the classifier is used as linear SVM classifier. .
- **Non-linear SVM:** Non-linear SVM is used for non-linearly separated data, which means that if a data set cannot be classified by a straight line, then the data is called non-linear data and the classifier used is called non-linear linear SVM classifier. [2]
</p>

### Decision Three

<p align="justify" >
In decision analysis, you can use a decision tree to visually and explicitly represent decisions and decision making. As its name implies, it uses a decision model in the form of a tree. Although it is a commonly used tool in data mining to derive a strategy to achieve a particular goal, it is also widely used in machine learning. [3]
</p>
<p align="justify" >
It is a tree-structured classifier, where the internal nodes represent the characteristics of a data set, the branches represent the decision rules, and each leaf node represents the result. [4]
</p>
<p align="justify" >
In a decision tree, there are two nodes, which are the decision node and the leaf node. Decision nodes are used to make any decision and have multiple branches, while leaf nodes are the result of those decisions and contain no more branches. Decisions or testing are made based on the characteristics of the given data set. [4]
</p>


<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/three.PNG)

</div>

<p align="justify" >
A decision tree is drawn upside down with its root at the top. In the image to the left, the bold black text represents an internal node / condition, according to which the tree is divided into branches / edges. The end of the branch that is no longer divided is the decision / leaf, in this case, whether the passenger died or survived, represented as red and green text respectively. [3]
</p>

### Logistic regression

<p align="justify" >
Logistic regression is a predictive modeling algorithm that is used when the variable Y is categorical binary. That is, it can only take two values ​​such as 1 or 0. The goal is to determine a mathematical equation that can be used to predict the probability of event 1. Once the equation is established, it can be used to predict the Y when only the X are known. [5]
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
The multilayer perceptron (MLP) is a complement to the forward neural network. It consists of three types of layers: the input layer, the output layer, and the hidden layer, as shown in the figure. The input layer receives the input signal to be processed. The required task, such as prediction and classification, is performed by the output layer. An arbitrary number of hidden layers that are placed between the input and output layers are the true computational engine of MLP. Similar to a feed-forward network in an MLP, data flows in the forward direction from the input layer to the output layer. Neurons in the MLP are trained with the backpropagation learning algorithm. MLPs are designed to approximate any continuous function and can solve problems that are not linearly separable. The main use cases for MLP are pattern classification, recognition, prediction, and approximation. [7]
</p>

<div  align="center" >

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/multilpe.PNG)

</div>

<p align="justify" >
Multilayer perceptrons are often applied to supervised learning problems: they train on a set of input-output pairs and learn to model the correlation (or dependencies) between those inputs and outputs. Training involves adjusting the parameters, or the weights and biases, of the model to minimize error. [8]
</p>

# Implementation

## SVM - Support Vector Machines

# Results


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
