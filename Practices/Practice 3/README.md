# Practice 3

# CORRELATION
## First, they import the necessary libraries, in this case, to make use of matrices, vectors, rows (for matrices), make correlations and use a spark session.
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

## They start creating and object called "CorrelationExample", using it to run the program just calling it by this name.
    object CorrelationExample {

## They proceed to define a main function, where they initialize a spark session in the spark value, importing spark implicits too.
    def main(args: Array[String]): Unit = {
        val spark = SparkSession
        .builder
        .appName("CorrelationExample")
        .getOrCreate()
        import spark.implicits._

## After that, they create a data value as an example of information, creating a data frame using that value and finalizing with the Pearson's and Spearman's correlations.
        val data = Seq(
        Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
        Vectors.dense(4.0, 5.0, 0.0, 3.0),
        Vectors.dense(6.0, 7.0, 0.0, 8.0),
        Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
        )

        val df = data.map(Tuple1.apply).toDF("features")
        val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
        println(s"Pearson correlation matrix:\n $coeff1")

        val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
        println(s"Spearman correlation matrix:\n $coeff2")

        spark.stop()
    }
    }

# CHI SQUARE
## First, they import the necessary libraries, in this case, to make use of vectors, Chi Square Tests and use a spark session.
    import org.apache.spark.ml.linalg.{Vector, Vectors}
    import org.apache.spark.ml.stat.ChiSquareTest
    import org.apache.spark.sql.SparkSession

## Then, they start creating and object called "ChiSquareTestExample", using it to run the program just calling it by this name.
    object ChiSquareTestExample {

## They proceed to define a main function, where they initialize a spark session in the spark value, importing spark implicits too.
    def main(args: Array[String]): Unit = {
        val spark = SparkSession
        .builder
        .appName("ChiSquareTestExample")
        .getOrCreate()
        import spark.implicits._

## After that, they create a data value as an example of information, creating a data frame using that value in two columns and finalizing with the pValues, the degrees of freedom and the statistics.
        val data = Seq(
        (0.0, Vectors.dense(0.5, 10.0)),
        (0.0, Vectors.dense(1.5, 20.0)),
        (1.0, Vectors.dense(1.5, 30.0)),
        (0.0, Vectors.dense(3.5, 30.0)),
        (0.0, Vectors.dense(3.5, 40.0)),
        (1.0, Vectors.dense(3.5, 40.0))
        )

        val df = data.toDF("label", "features")
        val chi = ChiSquareTest.test(df, "features", "label").head
        println(s"pValues = ${chi.getAs[Vector](0)}")
        println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
        println(s"statistics ${chi.getAs[Vector](2)}")

        spark.stop()
    }
    }

# SUMMARIZER
## First, they import the necessary libraries, in this case, to make use of vectors, Summarizer and use a spark session.
    import org.apache.spark.ml.linalg.{Vector, Vectors}
    import org.apache.spark.ml.stat.Summarizer
    import org.apache.spark.sql.SparkSession

## Then, they start creating and object called "SummarizerExample", using it to run the program just calling it by this name.
    object SummarizerExample {

## They proceed to define a main function, where they initialize a spark session in the spark value, importing spark implicits and Summarizer too.
    def main(args: Array[String]): Unit = {
        val spark = SparkSession
        .builder
        .appName("SummarizerExample")
        .getOrCreate()

        import spark.implicits._
        import Summarizer._

## They create a data value as an example of information 
    val data = Seq(
      (Vectors.dense(2.0, 3.0, 5.0), 1.0),
      (Vectors.dense(4.0, 6.0, 7.0), 2.0)
    )

## They create a data frame using that value in two columns
    val df = data.toDF("features", "weight")

## They create mean and variance (first values with Summary) 
    val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
      .summary($"features", $"weight").as("summary"))
      .select("summary.mean", "summary.variance")
      .as[(Vector, Vector)].first()

    println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")

## And then the mean and variance (second values without Summary).
    val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
      .as[(Vector, Vector)].first()

    println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")

    spark.stop()
     }
    }