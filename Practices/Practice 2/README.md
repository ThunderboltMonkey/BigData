# Practice 2

## We make the necessary imports for the use of LogisticRegression
    import org.apache.spark.ml.classification.LogisticRegression
    import org.apache.spark.sql.SparkSession

    import org.apache.log4j._
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().getOrCreate()
## Import the data we are gonna use for the algorithm
    val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/jonat/Desktop/BigData-master/Spark_LogisticRegression/advertising.csv")

    data.printSchema()

    val colnames = data.columns
    val firstrow = data.head(1)(0)
    println("\n")
    println("Example data row")
    for(ind <- Range(1, colnames.length)){
        println(colnames(ind))
        println(firstrow(ind))
        println("\n")
    }
    
## Prepare the Data for MachinLearning

## Do the next:
## - Rename the column "Clicked on Ad" to "label"
## - Take the following columns as features "Daily Time Spent on Site", "Age", "Area Income", "Daily Internet Usage", "Timestamp", "Male"
## - Create a new column called "Hour" from the Timestamp containing the "Hour of the click"
    val timedata = data.withColumn("Hour",hour(data("Timestamp")))

    val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male")
## Import VectorAssembler y Vectors

## Create a new VectorAssembler object called assembler for features 
    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vectors

    val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features"))



## Use randomSplit to create 70/30 split test and train data
    val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)

## Configure a Pipeline

## - Pipeline Amount
## - Create a new LogisticRegression object called lr

## - Create a new pipeline with the elements: assembler, lr

## - Fit the pipeline for the training set.


## - Take Results in Test set with transform

    import org.apache.spark.ml.Pipeline

    val lr = new LogisticRegression()

    val pipeline = new Pipeline().setStages(Array(assembler, lr))

    val model = pipeline.fit(training)

    val results = model.transform(test)
    
## Model evaluation

## For Metrics and Evaluation, import MulticlassMetrics

## Convert test results to RDD using .as and .rdd

## Initialize a MulticlassMetrics object

## Print the Confusion matrix
    import org.apache.spark.mllib.evaluation.MulticlassMetrics

    val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
    val metrics = new MulticlassMetrics(predictionAndLabels)

    println("Confusion matrix:")
    println(metrics.confusionMatrix)

    metrics.accuracy
