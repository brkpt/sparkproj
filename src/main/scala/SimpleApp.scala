/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, DataFrame}

// REGION TYPE,REGION,YEAR,WEEK,% WEIGHTED ILI,%UNWEIGHTED ILI,AGE 0-4,AGE 25-49,AGE 25-64,AGE 5-24,AGE 50-64,AGE 65,ILITOTAL,NUM. OF PROVIDERS,TOTAL PATIENTS
/*
case class ILIRecord(
  regionType: String,
  region: String,
  year: Int,
  week: Int,
  percWeightedILI: Float,
  unWeightedILI: Float,
  age0To4: Long,
  age25To49: Long,
  age25To64: Long,
  age5To24: Long,
  age50To64: Long,
  age65: Long,
  ILITotal: Long,
  numProviders: Int,
  totalPatients: Int) {
}*/

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
    val result = spark.sparkContext.parallelize(Seq(1,2,3,4,5), 4).map{ i => i*i }
//    val df: DataFrame = spark.read.format("csv").option("header", "true").load(dataFile)
//    df.createOrReplaceTempView("cdc")
//
//    val sqlDF = spark.sql("SELECT * FROM cdc where YEAR=2018")
//    sqlDF.show()
    spark.stop()
  }
}

