# Practice 5

## Random Forest

#### First, they import the necessary libraries, in this case, to make use of pipelines, random forest classification models, random forest classifiers, multiclass classification evaluators, string indexers, vector indexers and attributes. Then, they use a value called data to store the information of the file called "sample_libsvm_data.txt", creating a value called labelIndexer too where they fit the data and transforming that value. The team, automatically identify categorical features and index them, setting maxCategories so features with > 4 distinct values are treated as continuous. After that, they split the data into training and test sets (30% held out for testing), training a RandomForest model, converting indexed labels back to original labels and using pipelines to chain the indexers and forest. Finally they train the model fitting the trainingData into the pipeline, and transforming that model with testData to make their predictions. They show the results at the end.

    import org.apache.spark.ml.Pipeline
    import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
    import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
    import org.apache.spark.ml.attribute.Attribute

## Load and parse the data file, converting it to a DataFrame.
    val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

## Index labels, adding metadata to the label column. Fit on whole dataset to include all labels in index.
    val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
    val indexed =  labelIndexer.transform(data)
## Automatically identify categorical features, and index them. Set maxCategories so features with > 4 distinct values are treated as continuous.
    val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

## Split the data into training and test sets (30% held out for testing).
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

## Train a RandomForest model.
    val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)

## Convert indexed labels back to original labels.
    val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels) 
    
## Error <console>:32: error: value labelsArray is not a member of org.apache.spark.ml.feature.StringIndexer
    // labelConverter.transform(indexed)
    // Chain indexers and forest in a Pipeline.
    val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))

## Train model. This also runs the indexers.
    val model = pipeline.fit(trainingData)

## Make predictions.
    val predictions = model.transform(testData)

## Select example rows to display.
    predictions.select("predictedLabel", "label", "features").show(5)

## Select (prediction, true label) and compute test error.
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    println(s"Test Error = ${(1.0 - accuracy)}")

    val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]

    println(s"Learned classification forest model:\n ${rfModel.toDebugString}")