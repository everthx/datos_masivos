# Linear Support Vector Machine

### Description

They are also known by the acronym SVM for its acronym in English (Support Vector Machines). They can be used for both regression and classification.

They are a set of supervised learning algorithms developed by Vladimir Vapnik and his team at AT&T Labs.

An SVM builds a hyperplane or set of hyperplanes in a very high (or even infinite) dimensional space that can be used in classification or regression problems. A good separation between the classes will allow a correct classification.

#### Basic idea

Given a set of points, a subset of a larger set (space), in which each of them belongs to one of two possible categories, an algorithm based on SVM builds a model capable of predicting whether a new point (whose category we do not know) belongs to one category or the other.

As in most supervised classification methods, the input data (the points) are viewed as a p-dimensional vector (an ordered list of p numbers).

The SVM looks for a hyperplane that optimally separates the points of one class from that of another, which eventually could have been previously projected into a space of higher dimensionality.

In this concept of "optimal separation" is where the fundamental characteristic of SVM resides: this type of algorithm looks for the hyperplane that has the maximum distance (margin) with the points that are closest to itself. This is also why SVMs are sometimes referred to as top margin classifiers. In this way, the vector points that are labeled with one category will be on one side of the hyperplane and the cases that are in the other category will be on the other side.

#### Why are they called Support Vector Machines?

It's called "machine" in Spanish because of the "machine" learning part. The support vectors are the points that define the maximum margin of separation of the hyperplane that separates the classes. They are called vectors, instead of points, because these "points" have as many elements as there are dimensions in our input space. That is, these multi-dimensional points are represented with an n-dimensional vector.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/IMAGEN%20Linear%20Support%20Vector%20Machine.PNG)

### Sample Data - Regularization

Let's imagine we have two data classes, the Blue class and the Red class. What we want to do is draw a line that separates the 2 classes. In this way, when a new point arrives, we can see which of the 2 classes it belongs to depending on which side of the line it is on.

<img src="https://i.imgur.com/1itSHRR.png">

### How not to classify the data

<img src="https://i.imgur.com/AE6FJ2H.png">

<img src="https://i.imgur.com/lsPsIeF.png">

### Optimal way to classify the data

The aim is to maximize the margin between the two classes, so a line that better distinguishes the 2 classes is preferable since vector machines are machine learning techniques that find the best possible separation between the classes.

<img src="https://i.imgur.com/i47Tr3c.png">

### Regularization
Sometimes the data has noise because it is not well classified or because of the level of complexity of some points. In these cases, the SVM is told to generalize those few cases from the set.

### Kernel Trick in SVM - Some Applications

The main purpose of SVM is to form the hyperplane that maximizes the separation margin between classes, however, there are times when there is no way to find a hyperplane that allows separating two classes. In these cases we say that the classes are not linearly separable. To solve this problem we can use the kernel trick.

The kernel trick is to invent a new dimension in which we can find a hyperplane to separate the classes.

<img src="https://www.iartificial.net/wp-content/uploads/2019/04/SVM-kernel.png">


Some applications of support vector machines

Support vector machines were widely used before the deep learning era. For many applications the use of SVM was preferred over neural networks. The reason was that the mathematics of SVMs is very well understood and the property of obtaining the maximum separation margin was very attractive.

Some success stories of support vector machines are:

-Optical character recognition

   -OCR (Optical Character Recognition) is a transversal technology, applicable in different fields and sectors for the digitization of forms,
     administrative documents, reports, etc., since the advantages it offers are common to all of them.
     In the culture sector, for example, in the area of ​​heritage preservation, OCR is mainly applied in digitization processes
     of historical documents, on paper or microforms.
     
-Face detection for digital cameras to focus correctly

-Spam filters for email

-Recognition of images on board satellites
    -Helps to know which parts of an image have clouds, land, water, ice, etc.

<!-- START CRUZ -->
### Advantages and disadvantages of SVC.
### Advantage
> - Works great with a clear margin of separation.
> - It is effective in large spaces.
> - It is effective in cases where the number of dimensions is greater than the number of samples.
> - Uses a subset of training points in the decision function (called support vectors), so it is also memory efficien
> <img src="https://www.learnopencv.com/wp-content/uploads/2018/07/svm-linearly-separable-data.png">

### Disadvantages

> - It does not work well when we have a large data set because the training time required is longer
> - It also doesn't work very well when the dataset has more noise, that is, the target classes overlap
<img src="https://1.bp.blogspot.com/-CD6nja2DNDY/VgTft5YhWiI/AAAAAAAADEo/W7eTpexZ0fI/s1600/svm-predicted-classification-3-ring-data-resized-600.png">

> - SVM does not directly provide probability estimates, but rather they are calculated by costly five-time cross-validation. It is included in the related SVC method of the Python scikit-learn library.
<img src="https://i.stack.imgur.com/1fXzJ.png">
<!-- END -->

### Summary

Finally, we can say that the main objective is to segregate the data set in the best possible way and that the objective is to select a hyperplane with the maximum possible margin between support vectors in the given data set, being the vectors that define the margin of separation of the support vectors. In the case of non-separable classes, you can use the kernel trick to add a new dimension that allows such separation.

### Information sources

>Eduardo Morales, Jesús González, Hugo Jair Escalante. (2017). Máquinas de Soporte Vectorial. 26/11/2020, de INAOE Sitio web: https://ccc.inaoep.mx/~emorales/Cursos/NvoAprend/Acetatos/svm2017.pdf

>José Martínez Heras. (28/05/2019). Máquinas de Vectores de Soporte (SVM). 26/11/2020, de iartificial.net Sitio web: https://www.iartificial.net/maquinas-de-vectores-de-soporte-svm/

>---. (2020). Máquinas de vectores de soporte. 26/11/2020, de Wikipedia Sitio web: https://es.wikipedia.org/wiki/M%C3%A1quinas_de_vectores_de_soporte

### Repository

https://github.com/SalmaFabel/Linear-Support-Vector-Machine.git

