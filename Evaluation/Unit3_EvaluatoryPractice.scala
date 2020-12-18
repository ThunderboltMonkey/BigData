// 1- Import a simple Spark Session
import org.apache.spark.sql.SparkSession

// 2- Use the Error Reporting code lines
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// 3- Create a Spark Session
val spark = SparkSession.builder.getOrCreate()

// 4- Import the KMEANS library for the clustering algorithm
import org.apache.spark.ml.clustering.KMeans

// 5- Load the dataset: "Whole Customers Data"
val dataset = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Monkey/Desktop/Unit_3/Wholesale customers data.csv")

//File path for Jonathan
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("C:/Users/jonat/Desktop/BigData-master/Scala_Kmeans/Wholesale_customers_data.csv")
dataset.show(2)

// 6- Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; and name the set as "feature_data"
val  feature_data  = dataset.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")

// 7- Import the VectorAssembler and Vector libraries
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vector

// 8- Create a new Vector Assembler object for the feature columns as an input set, keeping in mind that there are no labels
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")

// 9- Use the assembler to transform the set "feature_data"
val  features = assembler.transform(feature_data)

// 10- Create the KMEANS model with k = 3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)

// 11- Evaluate the clusters using Within Set Sum of Squared Errors and print the centroids
val WSSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSSE")

//11.2. Show the centroids.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)