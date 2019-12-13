package sparkContext.SPARK_SQL

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object DataSet和RDD转换 {

  def main(args: Array[String]): Unit = {
    //1.创建sql基本环境
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("sql tranform")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val rdd: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(("lisi",22),("jiang",21)))

    import  spark.implicits._
    val df: DataFrame = rdd.toDF("name","age")
    df.show()

    //df转为ds
    println("*****ds*******")
    val ds: Dataset[person] = df.as[person]
    ds.show()
    ds.createTempView("person")

   //ds 转df
    println("*****df*******")
    val df2: DataFrame = ds.toDF()
    df2.show()


  }

}

case class person(name:String,age:Int)

