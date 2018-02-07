/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, DataFrame}

object SimpleApp {
  def isPrime(testValue: Int): Boolean = {
    val previousValues = (2 until testValue).toList
    val multipleMap = previousValues.foldLeft(Map.empty[Int,Boolean]){ (soFar, value) =>
      val isMultiple = if(testValue % value == 0) true else false
      soFar ++ Map(value -> isMultiple)
    }

    val multiples = multipleMap.foldLeft(0) { (z,m) =>
      val (k,v) = m
      if(v) {
        z+1
      } else {
        z+0
      }
    }
    multiples == 0
  }

  def main(args: Array[String]) {
    //val dataFile = "d:/Projects/spark/testproj/ILINet.csv" // Should be some file on your system
    //val conf = new SparkConf().setAppName("Spark Test").setMaster("local[4]")
    val spark = SparkSession.builder.master("local").appName("Spark Test").getOrCreate()
    val testValue = 5
    val result = spark.sparkContext.parallelize(2 until testValue, 4).map{ i =>
      if(testValue % i == 0) true else false
    }.collect()
//    val df: DataFrame = spark.read.format("csv").option("header", "true").load(dataFile)
//    df.createOrReplaceTempView("cdc")
//
//    val sqlDF = spark.sql("SELECT * FROM cdc where YEAR=2018")
//    sqlDF.show()
    spark.stop()
  }
}

