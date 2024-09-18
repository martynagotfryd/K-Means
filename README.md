# K-Means Project

## Project Requirements
Implement k-means algorithm.  Use euclidean distance to calculate distances between observations and centroids.
After run (or in command line arguments) program asks user for k - number of clusters.
Then, program assigns observations to clusters randomly and starts calculations of centroids and new clusters in a loop.
After each iteration (reassignment of clusters) your program should print:
- sum of distances between observations and centroid of observation's cluster (that is one value for all observations.)
- the purity of each cluster - percent of each label in each cluster.
Program should stop when there is no change in any cluster.
Program should work for any number of attributes, which is recognized automatically from the data file.
Program should be tested on iris dataset. K-means is a clustering algorithm so labels should be used only for calculations of purity.

## Project Overview
The K-Means Clustering Algorithm project implements unsupervised machine learning algorithm used for partitioning data into k distinct clusters based on feature similarity. This project demonstrates how to apply the K-Means algorithm to group a dataset into clusters and visualize the results.

## Features
- Cluster Data Points: Group data points into k clusters.
- Dynamic K Value: Adjust the number of clusters (k) as needed.
- Distance Calculation: Uses Euclidean distance to calculate the proximity of data points.
- Centroid Calculation: Automatically calculates and adjusts centroids for each cluster until convergence.

## K-Means Algorithm
The K-Means Algorithm is an iterative, unsupervised learning algorithm used to partition a dataset into k clusters. Each cluster is defined by a centroid (the mean of the points in the cluster), and each point is assigned to the cluster with the nearest centroid.

### Steps:
1. Initialize k random centroids.
2. Assign each data point to the nearest centroid, forming k clusters.
3. Recalculate the centroids based on the current clusters.
4. Repeat steps 2 and 3 until there is no change in any cluster.

## Installation
To run this project locally, follow these steps:

1. Clone the repository:
```
git clone git@github.com:martynagotfryd/K-Means.git
cd K-Means
```
2. Open the project: Open the project in your preferred Java IDE (IntelliJ IDEA, Eclipse, etc.).
3. Build the project: If you're using a build tool like Maven or Gradle, ensure the dependencies are installed. Otherwise, set up the project in your IDE.

## Usage
1. Input the dataset or provide your dataset file.
2. Specify the number of clusters (k) that you want to use.
3. Run the program from your IDE or terminal.
4. The program will output the clustered data points and optionally visualize the clusters.
