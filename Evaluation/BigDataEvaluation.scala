// BigData Evaluatory Practice

// 1) Initialize a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
// 2) Load the CSV file named "Netflix_2011_2016.csv"
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

// 3) Which are the column names?
df.columns

// 4) How is the schema?
df.printSchema()

// 5) Print the first 5 columns
df.columns(1:5)  

// 6) Use the funtion describe() to learn about the DataFrame
df.describe().show

// 7) Create a new DateFrame with a new column named "HV Ratio" which will be the relation
// between the price of the column "High" and the column "Volume" from all the negotiated actions per day.
// (Hint: It's a column operation)
 val df2 =df.withColumn("HV_Ratio", df("High")/df("Volume"))
 df2.show()

// 8) Which day had the highest value on the column "Close"
//First attempt, shows all instead of 1 result
df.groupBy(dayofmonth(df("Date")).alias("Day")).max("Close").sort(asc("Day")).show()

//Used for conformation so we can be sure the result is correct
df.select(max("Close")).show()

//Second attempt, Shows the final result 
df.select("Date","Close").groupBy(dayofweek(df("Date")).alias("Day")).max("Close").sort(asc("Day")).show(1)


// 9) In your own words on a code commentary, describe: Which is the significate of the "Close" column
/*
CASTILLO SOLIS FABIAN EDUARDO
This column it´s the calculation of the mean between the columns "High" and "Low"
*/

//Angeles Valadez Jonathan
/*
Close describe basicamente el promedio con el que se cerro el dia.
*/

// 10) Calculate the max and min of the "Volumne" column
df.select(max("Volume")).show()
df.select(min("Volume")).show()

// In Scala/Spark $ syntax answer the following:
// Hint: Basically it's similar to the session dates, you'll have to create another DataFrame
// to answer some of the points below

// 11-A) How many days of the "Close" column were less than $600?
val lessTSH = df.filter($"Close" < 600).count()

// 11-B) Which percent of the time of the "High" column was higher than $500?
val time = df.filter($"High" > 500).count()
val time1 = time * .100

// 11-C) What is the Pearson correlation between the "High" and "Volume" column
df.select(corr("High", "Volume").alias("correlacion")).show()

// 11-D) What is the highest value of the "High" column per year?
df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()

// 11-E) What is the mean of the "Close" column for each month of the calendary?
df.groupBy(month(df("Date")).alias("Month")).avg("Close").sort(asc("Month")).show()