# Practice 9

# ONE VS REST

## We have to import the following libraries
    import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

## Importing our data
    var inputData = spark.read.format("libsvm").load("/opt/spark/data/mllib/sample_multiclass_classification_data.txt")

## We have to split the data into the training (80%) and test (20%) set
    val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

## Create our classifier 
    val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

## Create our OneVsRest classifier
    val ovr = new OneVsRest().setClassifier(classifier)

## Train the model with the training data
    val ovrModel = ovr.fit(train)

## Make our predictions with the test set in the model
    val predictions = ovrModel.transform(test)

## Create our evaluator
    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

## Calculate the accuracy from our model
    val accuracy = evaluator.evaluate(predictions)

## And finally print the result
    println(s"Test Error = ${1 - accuracy}")