package sparkContext.SPARK_SQL.文件读取和保存

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadFileTest {
  def main(args: Array[String]): Unit = {
    //创建配置信息
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //1.不是Parquet file会报错
//    val df: DataFrame = spark.read.load("in/json/user.json")
//    df.show()

    println("**********parquet**********")
    //2.Parquet file
    //Spark SQL的默认数据源为Parquet格式，所以不会报错
   val df: DataFrame = spark.read.load("in/resources/users.parquet")
    df.show()

    /**
      * 当数据源格式不是parquet格式文件时，需要手动指定数据源的格式。
      * 数据源格式需要指定全名
      * （例如：org.apache.spark.sql.parquet），
      * 如果数据源格式为内置格式，则只需要指定简称定json,
      *  parquet, jdbc, orc, libsvm, csv, text
      * 来指定数据的格式。
      */
    println("**********json**********")
    val jsondf: DataFrame = spark.read.format("json").load("in/json/user.json")
    //spark.read.json("in/json/user.json") //这样也行
    jsondf.show()



  }

}
