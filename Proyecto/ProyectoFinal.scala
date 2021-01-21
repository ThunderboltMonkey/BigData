//Necessary libraries Decision Tree.
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler

//Necessary libraries Kmeans
import org.apache.spark.ml.clustering.KMeans

//Necessary libraries SupportVector.
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression

//Cada URL cambia del archivo
//
//Decision Trees
//

//Error level code.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Spark session.
val spark = SparkSession.builder.appName("DecisionTree").getOrCreate()

//Reading the csv file.
val df  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/jonat/Desktop/iris-master/iris-master/iris.csv")

//Indexing.
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")

//Vector of the numeric category columns.
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features"))

//Transforming the indexed value.
val features = vectorFeatures.transform(indexed)

//Renaming the column y as label.
val featuresLabel = features.withColumnRenamed("species", "label")

//Union of label and features as dataIndexed.
val dataIndexed = featuresLabel.select("label","features")

//Creation of labelIndexer and featureIndexer for the pipeline, Where features with distinct values > 4, are treated as continuous.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(dataIndexed)

////////////////////////////////////
val t1 = System.nanoTime
/* Code to inspect time elapsed */
////////////////////////////////////
//*****************************************************************************
//Training data as 70% and test data as 30%.
val Array(trainingData, testData) = dataIndexed.randomSplit(Array(0.7, 0.3))

//Creating the Decision Tree object.
val decisionTree = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

//Creating the Index to String object.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

//Creating the pipeline with the objects created before.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, decisionTree, labelConverter))

//Fitting the model with training data.
val model = pipeline.fit(trainingData)

//Making the predictions transforming the testData.
val predictions = model.transform(testData)

//Showing the predictions.
predictions.select("predictedLabel", "label", "features").show(5)

//Creating the evaluator.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

//Accuracy.
val accuracy = evaluator.evaluate(predictions)

//****************************************************************
// Finsihed code, evaluate time
val duration = (System.nanoTime - t1) / 1e9d
//
//Accuracy and Test Error.
println(s"Accuracy: ${(accuracy)}")
println(s"Test Error: ${(1.0 - accuracy)}")


//********************************************
//SVM
//********************************************

//Spark session.
val spark = SparkSession.builder.appName("SVM").getOrCreate()

//Reading the csv file.
val df  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/jonat/Desktop/iris-master/iris-master/iris.csv")

//Indexing.
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")

//Vector of the numeric category columns.
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features"))

//Transforming the indexed value.
val features = vectorFeatures.transform(indexed)

//Renaming the column y as label.
val featuresLabel = features.withColumnRenamed("species", "label")

//Union of label and features as dataIndexed.
val dataIndexed = featuresLabel.select("label","features")

//Creation of labelIndexer and featureIndexer for the pipeline, Where features with distinct values > 4, are treated as continuous.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(dataIndexed)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(dataIndexed)

////////////////////////////////////
val t1 = System.nanoTime
/* Code to inspect time elapsed */
////////////////////////////////////
//*****************************************************************************

//Training data as 70% and test data as 30%.
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3))

//Linear Support Vector Machine object.
val supportVM = new LinearSVC().setMaxIter(10).setRegParam(0.1)
    
//Fitting the trainingData into the model.
val model = supportVM.fit(trainingData)

//Transforming testData for the predictions.
val predictions = model.transform(testData)

//Obtaining the metrics.
val predictionAndLabels = predictions.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

//****************************************************************
// Finsihed code, evaluate time
val duration = (System.nanoTime - t1) / 1e9d
//

//Confusion matrix.
println("Confusion matrix:")
println(metrics.confusionMatrix)

//Accuracy and Test Error.
println("Accuracy: " + metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")


//*********************************
//K MEANS
//*********************************

//Create a Spark Session
val spark = SparkSession.builder.getOrCreate()

//Load the dataset: "Whole Customers Data"
val dataset = spark.read.option("header","true").option("inferSchema", "true").csv("C:/Users/jonat/Desktop/iris - iris.csv")

//Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen; and name the set as "feature_data"
val  feature_data  = dataset.select("sepal_length","sepal_width","petal_length","petal_width","species")
feature_data.show(2)

//Import the VectorAssembler and Vector libraries
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vector

//Create a new Vector Assembler object for the feature columns as an input set, keeping in mind that there are no labels
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width","species")).setOutputCol("features")

////////////////////////////////////
val t1 = System.nanoTime
/* Code to inspect time elapsed */
////////////////////////////////////
//*****************************************************************************

//Use the assembler to transform the set "feature_data"
val  features = assembler.transform(feature_data)

//Create the KMEANS model with k = 3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)

//Evaluate the clusters using Within Set Sum of Squared Errors and print the centroids
val WSSSE = model.computeCost(features)
//****************************************************************
// Finsihed code, evaluate time
val duration = (System.nanoTime - t1) / 1e9d
//

println(s"Within set sum of Squared Errors = $WSSSE")

//Show the centroids.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
