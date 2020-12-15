// We have to import the following two libraries that are necessary to use the MLPC
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
//Points 1-9 assigned to explain to Castillo Solis Fabian Eduardo
//Points 10-19 assgined to explain to Angeles Valadez Jonathan
// We have to import the following library that will allow us to carry out the corresponding labeling
import org.apache.spark.ml.feature.StringIndexer 

// We have to import the following library that we are going to need to join all the columns in a single vector
import org.apache.spark.ml.feature.VectorAssembler

// We have to initialize our spark session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// We import our data and save it in the dataframe "df"
// File location will vary depending on where you have the file saved.
 val df = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/jonat/Desktop/iris-master/iris-master/iris.csv")

//1 We print the column names
df.columns

//2 We print the schema of the data
df.printSchema()

//3 We take the first 5 columns
df.columns.take(5)  

//4 We use the describe method to learn more about the data
df.describe().show

//5 We use the StringIndexer method to transform a column of label strings into a column of label indexes.
// In this case, the column that we are going to transform is the column "species" and we are going to assign
// the name of "indexedLabel" to the new column, finally we are going to adjust the data of the dataframe "df"
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)

//6 Now what we will do is replace the column "species" with our column "indexedLabel" and we will show it with the name of "label"
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")

//7 We use the "describe" and "show" methods to view the new structure with the aforementioned changes
indexed.describe().show()

//8 We use the VectorAssembler method to join several columns in a vector
// Our assembler takes the columns of the dataset and transforms them into the vector called "features"
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")

//9 Then we create our features value and with the transform function of our assembler we transform our 
// "indexed" data set that we remember is the one that is already processed
val  features = assembler.transform(indexed)

//10 We use the StringIndexed method again to pass it the "label" column and obtain the "indexedLabel" column.
// Finally, we adjust the processed data of the "indexed" dataframe.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)

//11 We print the labels that were found
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")

//12 We print our dataframe and we can see that the last column is the set of all the previous columns
features.show

//13 We separate our dataset 70% in training data and 30% in test data, we establish the randomness seed
val splits = features.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

//14 We establish our layers for the neural network, of size 4 for the input layer (features), two in the
// intermediate layer (hidden layer) of size 5 and 4, finally our output layer of size 3 (classes)
val layers = Array[Int](4, 5, 4, 3)

//15 We use the "MultilayerPerceptronClassifier" method to create our trainer and we pass their respective
// parameters such as layers, block size, randomness seed and maximum iterations.
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//16 We train the model with our trainer and pass it the training data (70%)
val model = trainer.fit(train)

//17 We test our model with the test data (30%)
val result = model.transform(test)

//18 We make our prediction and show it
val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show

//19 We evaluate the precision of our model and finally we print this value
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")