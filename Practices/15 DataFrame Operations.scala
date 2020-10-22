# Practice number 5

## We have practice 15 different operations with dataframes

        // Setup process for the dataframe
        import org.apache.spark.sql.SparkSession

        // Session for Spark created and assign it to the val "spark"
        val spark = SparkSession.builder().getOrCreate()

        // Setup from where a which will be the data source and assign it to the val "df"
        val df = spark.read.option("header", "true").option("inferSchema","true")csv("CitiGroup2006_2008")

        // Print the schema of the dataframe
        df.printSchema()

# Operation 1

        df.count()
        // It returns the count of al the rows on the dataframe

# Operation 2

        df.head(n=10)
        // It returns the first 10 rows of the dataframe

# Operation 3

        df.takeAsList(n=10)
        // It returns the first 10 rows of the dataframe as a List

# Operation 4

        df.columns
        // It shows all the columns composing the dataframe

# Operation 5

        df.dtypes
        // It shows the name of the column and the type of it

# Operation 6

        df.explain
        // It prints the physical plan to the console for debugging purposes

# Operation 7

    df.isEmpty
    // It returns a false is the dataframe is not empty, returns true if it's empty

# Operation 8

    df.isLocal
    // Returns true if the collect and take methods can be run locally

# Operation 9

    df.alias(alias="Test").show()
    // Returns a new Dataset with an alias set

# Operation 10

    df.orderBy(sortCol="Volume").show()
    // It returns a new dataset sorted by the given expressions.

# Operation 11

    df.select(avg("Volume")).show()
    // It returns the average of the selected column

# Operation 12

    df.select(min("Volume")).show()
    // It returns the minimun of the selected column

# Operation 13

    df.select(max("Volume")).show()
    // It returns the maximum of the selected column

# Operation 14

    df.select(sum("Volume")).show()
    // It returns the summatory of the selected column

# Operation 15

    df.select(mean("Volume")).show()
    // It returns the mean of the selected column