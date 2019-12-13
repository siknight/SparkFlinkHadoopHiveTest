package sparkContext.SPARK_SQL.JDBC操作

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, DataFrameWriter, Row, SparkSession}

object WriteToMysql {
  def main(args: Array[String]): Unit = {
    //创建配置信息
    val conf: SparkConf =
      new SparkConf().setAppName("spark sql").setMaster("local")
    //创建sparksession
    //创建SparkSQL的环境对象
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val sc: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(("lisi",22),("jiang",20)))

    import spark.implicits._
    val df: DataFrame = sc.toDF("username","age")
 //方式一
      println("******方式一******")
//
//
      df.write.format("jdbc").mode("append")
        .option("url", "jdbc:mysql://localhost:3306/rdd")
        .option("dbtable", "user").option("user", "root")
        .option("password", "jiang").save()
//方式二
    println("******方式二******")
    val connectionProperties  = new Properties()
    connectionProperties.setProperty("user","root")
    connectionProperties.setProperty("password","jiang")
    df.write.mode("overwrite").jdbc("jdbc:mysql://localhost:3306/rdd",
      "user2", connectionProperties)








  }

}

case class user(username:String,age:Int)
