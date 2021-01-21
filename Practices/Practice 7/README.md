# Practice 7

# Multilayer Perceptron Classifier

## We have to import the following libraries: MultilayerPerceptronClassifier y MulticlassClassificationEvaluator
    import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

## We have to initialize our spark session
    import org.apache.spark.sql.SparkSession

## We create the object "MultilayerPerceptronClassifierExample"
    object MultilayerPerceptronClassifierExample {

## We have to initialize our function
    def main(): Unit = {
    
## We have to initialize our spark session
    val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierExample").getOrCreate()

## We have to import our data
val data = spark.read.format("libsvm") .load("sample_multiclass_classification_data.txt")

## We have to split the date into the training (60%) and test (40%) set
    val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
    val train = splits(0)
    val test = splits(1)

## The layers for the neural network are the following:The input layer it's size 4 (features), two hidden layers, one it's size 5 and the other one it's size 4 and finally one size 3 for the output
    val layers = Array[Int](4, 5, 4, 3)

## Create our trainer
    val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

## Adjust the model to the training data
    val model = trainer.fit(train)

## Calculate our prediction and accuracy from the model
    val result = model.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

## Print the results
    println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
     }
    }
