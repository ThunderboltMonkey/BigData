# Practice 10

## We import our necesarry libraries for NaiveBayes and the Evaluator.
    import org.apache.spark.ml.classification.NaiveBayes
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

## Create and load the saved data to libsvm format as a DataFrame
    val data = spark.read.format("libsvm").load("C:/Users/jonat/Desktop/NaiveBayes-main/scala/sample_libsvm_data.txt")
    data.show(2)

## Separate our data into a training set and test set, 70% for Training, 30% for Testing
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)

## We train our model with NaiveBayes
    val model = new NaiveBayes().fit(trainingData)

## Transform out data so we can show a select ammount
    val predictions = model.transform(testData)
    predictions.show()

## Select our prediction and accuracy, also calculate our test errors
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

    val accuracy = evaluator.evaluate(predictions)

    println(s"Test set accuracy = $accuracy")
