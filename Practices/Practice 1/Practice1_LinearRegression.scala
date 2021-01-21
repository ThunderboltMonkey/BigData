// Practice 1
We import the linear regression library with the code:

// We have to import the LinearRegression library
import org.apache.spark.ml.regression.LinearRegression

//And we use the next code to config the error level
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// We have to initialize our spark session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// We have to import our data from the file Clean-Ecommerce.csv
val path = "C:/Users/Monkey/Desktop/Unit_3/Clean-Ecommerce.csv"
val cleanecommerce = spark.read.option("header", "true").option("inferSchema","true")csv(path)

// We print the data frame schema and show the first row to see if the importation was successful.
cleanecommerce.printSchema()
cleanecommerce.show(1)

// We have to import the libraries VectorAssembler and Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// We create a new data frame from the cleanecommerce data frame. 
// This new data frame is called df, with the column "Yearly Amount Spent" as "label" and only numeric columns.
val df = cleanecommerce.select($"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent".as("label"))

// We have to create the value assembler with VectorAssembler() function. 
// We use this object in order to create the columns of df in one only column called features.
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership")).setOutputCol("features")

// We transform the data frame df with the transform() function, using the assembler created before.
// We use the union between the column that we called label and the new column called features. We save the result in the new data frame df2.
val df2 = assembler.transform(df).select($"label", $"features")

// We create an object called lr, that represents a new LinearRegression() object.
val lr = new LinearRegression()

// We fit the df2 data frame using the lr object created before and saving the result in the lrModelo value.
val lrModelo = lr.fit(df2)

// And finally, we make a summary of the model creating an object called trainingSummary with the summary function, and using this object to obtain the results that we were looking for.
val trainingSummary = lrModelo.summary

// We show the cofficients and the intercept for the linear regression using lrModelo.
println(s"Coefficients: ${lrModelo.coefficients} Intercept: ${lrModelo.intercept}")

// Now we can show the residuals.
trainingSummary.residuals.show()

//  Finally we show the root meand squared error (RMSE), the mean squared error (MSE) and the r².
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"MSE: ${trainingSummary.meanSquaredError}")
println(s"r2: ${trainingSummary.r2}")